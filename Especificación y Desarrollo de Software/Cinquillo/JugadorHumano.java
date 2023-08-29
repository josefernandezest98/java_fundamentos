import java.util.InputMismatchException;
import java.util.Scanner;

public class JugadorHumano extends Jugador{
	public JugadorHumano(String nom){
		super(nom);
	}
	

	public Carta elegir(Mesa m){
		Carta c=this.cartas.get(0);;
		int x=0;
		boolean exception;
		System.out.println("Mesa: ");
		m.mostrarMesa();
		
		for(int i=0;i<this.cartas.size();i++){
			System.out.print("[" + i + "]");
			this.cartas.get(i).mostrar();
		}
		
		System.out.println("");
		Scanner ent1=new Scanner(System.in);
		
		do{
			try
			{
				System.out.println("Elige una carta válida por favor:");
				exception=false;
				x=ent1.nextInt();
				c=this.cartas.get(x);
			}
			catch(Exception e) 
			/*
			Puede salir un InputMismatch Exception o un NullPointer Exception 
			así que como no me interesa saber que excepcion es pongo un Excepcion generica
			 */
			{
				exception=true;
			}
			finally{
				ent1.nextLine();
			}
			}
			while(exception || !m.sePuedePoner(c));
		this.cartas.remove(x);
		return c;
	}
	
	public void recibirCarta(Carta c){
		int oros, copas, espadas, bastos;
		oros=numero(0);
		copas=numero(1);
		espadas=numero(2);
		bastos=numero(3);
		int i;
		boolean puesta=false;
		switch (c.getPalo()) {
		case 0:
			i=0;
			while(i<oros && puesta==false){
				if(this.cartas.get(i).getValor()>c.getValor()){
					puesta=true;
				}
				else{
					i++;
				}
			}
			this.cartas.add(i,c);
			break;
			
		case 1:
			i=oros;
			while(i<oros+copas && puesta==false){
				if(this.cartas.get(i).getValor()>c.getValor()){
					puesta=true;
				}
				else{
					i++;
				}
			}
			this.cartas.add(i,c);
			break;
		case 2:
			i=oros+copas;
			while(i<oros+copas+espadas && puesta==false){
				if(this.cartas.get(i).getValor()>c.getValor()){
					puesta=true;
				}
				else{
					i++;
				}
			}
			this.cartas.add(i,c);
			break;
			
		case 3:
			i=oros+copas+espadas;
			while(i<oros+copas+espadas+bastos && puesta==false){
				if(this.cartas.get(i).getValor()>c.getValor()){
					puesta=true;
				}
				else{
					i++;
				}
			}
			this.cartas.add(i,c);
			break;

		default:
			break;
		}
	}
	
}
/// OK