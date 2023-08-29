import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Partida {
	
	private Mesa m;
	private List<Jugador> jugadores;
	private int turno, numHumanos, numMaquinas;
	private boolean finalizado;
	
	public Partida(int numH, int numM){
		this.m= new Mesa();
		this.jugadores = new ArrayList<>();
		this.turno=0;
		this.numHumanos=numH;
		this.numMaquinas=numM;
		this.finalizado=false;
	}
	
	public void iniciarPartida(){
		
		this.m.anadirTodas();
		
		for(int i=0;i<this.numMaquinas;i++){
			String nom="CPU "+(i+1);
			Jugador j= new JugadorMaquina(nom);
			this.jugadores.add(j);
		}
		
		for(int i=0;i<this.numHumanos;i++){
			Scanner ent= new Scanner(System.in);
			String nom;
			boolean iguales;
			do{
				System.out.println("Introduzca el nombre del jugador " + (i+1));
				nom=ent.nextLine();
				iguales=false; //Lo ponemos en false y ahora recorremos para ver si el nombre propuesto es igual al de otro jugador
				for(int j=0;j<this.jugadores.size() && iguales==false;j++){
					if(nom.equals(this.jugadores.get(j).getNombre())){
						System.out.println("Ese nombre ya está en uso");
						iguales=true;
					}
				}
			}
			while(iguales==true);
			
			Jugador j= new JugadorHumano(nom);
			this.jugadores.add(j);
		}

		
		int numCartas;
		
		if((this.numHumanos+this.numMaquinas)<5){
			numCartas=10;
		}
		else{
			numCartas=40/(this.numHumanos+this.numMaquinas);
		}
		for (int i = 0; i < this.jugadores.size(); i++) {
			for (int j = 0; j < numCartas; j++) {
				this.jugadores.get(i).recibirCarta(m.darCartaRobar());	
			}
		}
		
//		Ahora vamos a ver quien empieza
		
		boolean primero=false;
		Carta co = new Carta(5,0); //Creamos el 5 de oros
		Carta cp = new Carta(5,1); //Creamos el 5 de copas
		Carta ce = new Carta(5,2); //Creamos el 5 de espadas
		Carta cb = new Carta(5,3); //Creamos el 5 de bastos
		
		for(int i=0;i<this.jugadores.size() && primero==false;i++){
			if(this.jugadores.get(i).eliminarCarta(co)==true){
				this.m.colocarCarta(co);
				this.turno=(i+1)%(this.jugadores.size());//Pasamos el turno porque ya se la hemos puesto nosotros
				primero=true;
			}
		}
		
		for(int i=0;i<this.jugadores.size() && primero==false;i++){
			if(this.jugadores.get(i).eliminarCarta(cp)==true){
				this.m.colocarCarta(cp);
				this.turno=(i+1)%(this.jugadores.size()) ;//Pasamos el turno porque ya se la hemos puesto nosotros
				primero=true;
			}
			if((this.jugadores.get(i).eliminarCarta(ce)==true)&&(primero==false)){
				this.m.colocarCarta(ce);
				this.turno=(i+1)%(this.jugadores.size());//Pasamos el turno porque ya se la hemos puesto nosotros
				primero=true;
			}
			if((this.jugadores.get(i).eliminarCarta(cb)==true)&&(primero==false)){
				this.m.colocarCarta(cb);
				this.turno=(i+1)%(this.jugadores.size());//Pasamos el turno porque ya se la hemos puesto nosotros
				primero=true;
			}
		}

		if(primero==false){
			Random r = new Random();
			this.turno=r.nextInt(this.jugadores.size());
		}
		
	}
	
	public boolean finalizado(){
		return finalizado;
	}
	
	public Jugador ganador(){
		Jugador ganador=this.jugadores.get(0);
		int minimo=ganador.numCartas();
		for(int i=1;i<this.jugadores.size();i++){
			if(this.jugadores.get(i).numCartas()<=minimo){
				ganador=this.jugadores.get(i);
				minimo=ganador.numCartas();
			}
		}
		return ganador;
	}
	
	public void clasificacion(){
		int posicion=1;
		while(!this.jugadores.isEmpty()){
			Jugador jugador= this.ganador();
			this.jugadores.remove(jugador);
				if(jugador.numCartas()==0){
					System.out.println("El ganador de la partida es: ");
					System.out.println("----> "+jugador.getNombre()+" <----" + " ¡Enhorabuena! ");
					System.out.println("A continuacion se muestran los demás participantes: ");
				}
				else{
					System.out.println("En la posicion numero: " + posicion + " El jugador " + jugador.getNombre() + " se ha quedado con " + jugador.numCartas() +" cartas. ");
				}
			posicion++;
		}
		
	}
	
	public void jugada(){
		Jugador j = this.jugadores.get(this.turno);
		
		if(j.puedeJugar(this.m)){
			System.out.println("El jugador " + j.getNombre() + " puede jugar");
			this.m.colocarCarta(j.elegir(this.m));
			if(j.numCartas()==0){
				this.finalizado=true;
			}
		}
		else{
			System.out.println("El jugador " + j.getNombre() + " no puede jugar");
			if(!this.m.vacioRobar()){
				Carta robada = this.m.darCartaRobar();
				if(this.m.sePuedePoner(robada)){
					System.out.println("El jugador " + j.getNombre() + " puede colocar la carta robada y coloca el " + robada.toString());
					m.colocarCarta(robada);
				}
				else{
					System.out.println("El jugador " + j.getNombre() + " no puede poner la carta robada");
					j.recibirCarta(robada);
				}
			}
			else{
				System.out.println("El jugador " + j.getNombre() + " no puede robar y pierde el turno");
			}
		}
		
		this.turno=(this.turno+1)%this.jugadores.size();
		
	}
}/// OK
