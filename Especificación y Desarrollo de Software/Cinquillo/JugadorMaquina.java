
public class JugadorMaquina extends Jugador{

	public JugadorMaquina(String nom) {
		super(nom);
	}

	public Carta elegir(Mesa m) {	//Esta máquina está programada para hacer la jugada menos "eficiente" ya que prioriza colocar los 5 y después la primera que encuentre y pueda poner
		if(this.cartas.get(0).getValor()==5){	//Sabemos que hay alguna carta que se puede colocar, si es la primera devolvemos
			Carta aux= new Carta(this.cartas.get(0).getValor(),this.cartas.get(0).getPalo()); /// SE PUEDE DEVOLVER DIRECTAMENTE LA CARTA, NO HACE FALTA CREAR OTRA
			System.out.println("El jugador "+ this.getNombre() + " ha colocado la carta " + aux.toString());
			this.cartas.remove(0);
			return aux;
		}
		else{	//Pero si el primero no es el 5, no podemos asegurar que la primera se pueda colocar, por tanto la recorremos y ponemos la primera que encontremos
			int i=0;
			boolean encontrado=false;
			while((i<this.cartas.size())&& !encontrado){
				if(m.sePuedePoner(this.cartas.get(i))){
					encontrado=true;
				}
				else{
					i++;
				}
			}
			
			Carta aux= new Carta(this.cartas.get(i).getValor(),this.cartas.get(i).getPalo()); /// SE PUEDE DEVOLVER DIRECTAMENTE LA CARTA, NO HACE FALTA CREAR OTRA
			System.out.println("El jugador "+ this.getNombre() + " ha colocado la carta " + aux.toString());
			this.cartas.remove(i);
			return aux;
		}	
	}

	public void recibirCarta(Carta c) {
		if(c.getValor()==5){
			this.cartas.add(0,c);
		}
		else{
			int i=0;
			boolean puesta=false;
			while(i<this.cartas.size() && this.cartas.get(i).getValor()==5){
				i++;
			}
			while(i<this.cartas.size() && puesta==false){
				if(Math.abs(c.getValor()-5)>Math.abs(this.cartas.get(i).getValor()-5)){
					this.cartas.add(i,c);
					puesta=true;
				}
				else{
					i++;
				}
			}
			if(i==this.cartas.size()){
				this.cartas.add(c); //Añadimos al final porque no ha hecho falta incluirla antes
			}
		}
	}
	
	
	/// OK

}
