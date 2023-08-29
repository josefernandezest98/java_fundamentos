import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mesa {
	private List<Carta> montonRobar;
	private DobleCola<Carta>[] cartasColocadas= new DobleCola[4];
	
	public Mesa (){
		this.montonRobar=new ArrayList<>();
		for(int i=0;i<=3;i++){
			cartasColocadas[i]=new DobleCola<Carta>();
		}
	}
	
	public void anadirTodas(){
		Carta c;
		for(int i=0;i<=3;i++){
			for(int j=1;j<=10;j++){
				c=new Carta(j,i);
				montonRobar.add(c);
			}
		}
	}
	
	public Carta darCartaRobar(){
		if(this.vacioRobar()==true){
			return(null);
		}
		Random r = new Random();
		int num=r.nextInt(this.montonRobar.size());
		Carta c=this.montonRobar.get(num);
		this.montonRobar.remove(num);
		return(c);
	}
	
	public void colocarCarta(Carta c){
		int i=c.getPalo();
		if(this.sePuedePoner(c)==true){
			if(!this.cartasColocadas[i].vacia()){		//Si la cola no está vacía, hace esto
				if(this.cartasColocadas[i].getPrimero().equals(c.CartaMenos())){
					cartasColocadas[i].anadirPrincipio(c);
				}
				else{
					cartasColocadas[i].anadirFinal(c);		//Este es el otro caso, solo hay dos opciones
				}
			}
			else{
				cartasColocadas[i].anadirPrincipio(c);		//Este es el caso en el que pasemos el 5
			}
		}
	}
	
	public boolean vacioRobar(){
		return(montonRobar.isEmpty());
	}
	
	public boolean sePuedePoner(Carta c){
		int i=c.getPalo();					
		if(this.cartasColocadas[i].vacia()){ //El caso de que esté vacía la fila de su palo, ya que el 5 siempre se puede poner
			if(c.getValor()==5){
				return true;
			}
			else{
				return false;
			}
		}
		return(this.cartasColocadas[i].getPrimero().equals(c.CartaMenos())||this.cartasColocadas[i].getUltimo().equals(c.CartaMas()));
	}
	
	public void mostrarMesa(){
		for(int i=0;i<=3;i++){
			this.cartasColocadas[i].mostrar();
			System.out.println("");
		}
	}
	

	/// OK
}
