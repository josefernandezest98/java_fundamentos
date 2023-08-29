import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {
	protected List<Carta> cartas;
	private String nombre;
	
	public Jugador(String nom){
		this.cartas=new ArrayList<>();
		this.nombre=nom;
	}
	
	public String getNombre(){	
		return this.nombre;
	}
	
	public boolean puedeJugar(Mesa m){
		for(int i=0;i<this.cartas.size();i++){
			if(m.sePuedePoner(this.cartas.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public int numCartas(){
		return this.cartas.size();
	}
	
	public boolean finalizado(){
		return this.cartas.size()==0;
	}
	
	public int numero(int i){	//El entero es el palo, 0,1,2,3, oros, copas, espadas y bastos respectivamente	
		int cont=0;
		for(int j=0;j<this.cartas.size();j++){
			if(this.cartas.get(j).getPalo()==i){
				cont++;
			}
		}
		return cont;
	}
	
	public boolean eliminarCarta (Carta c){
		int palo=c.getPalo();
		int valor=c.getValor();
		for(int i=0;i<this.cartas.size();i++){
			if(this.cartas.get(i).getValor()==valor && this.cartas.get(i).getPalo()==palo){ /// PODÉIS USAR EL MÉTODO equals DE CARTA
				this.cartas.remove(i);
				return true;
			}
		}
		return false;	
	}
	
	
	public abstract Carta elegir(Mesa m);
	
	public abstract void recibirCarta(Carta c);
}
/// OK
