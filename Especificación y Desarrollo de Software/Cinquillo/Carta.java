
public class Carta {
	private int valor;
	private int palo;	//Palos entre los valores 0,1,2,3, oros, copas, espadas y bastos respectivamente 
	
	public Carta (int v, int p){
		this.valor=v;
		this.palo=p;
	}
	public int getValor() {
		return valor;
	}
	public int getPalo() {
		return palo;
	}
	
	public String toString(){
		int val;
		if(this.getValor()<=7){
			val=this.getValor();
		}
		else{
			val=this.getValor()+2;   //Paso los números 8,9 y 10 a 10, 11 y 12 simulando sota caballo y rey
		}
		String v=val+" de ";
		switch(this.getPalo()){
		case 0:
			return(v+"Oros");
		case 1:
			return(v+"Copas");
		case 2:
			return(v+"Espadas");
		case 3:
			return(v+"Bastos");
		default:  					//No llegará nunca aquí
			return(v);
		}
	}
	
	public void mostrar(){
		System.out.print(this.toString()+", ");
	}
	
	public Carta CartaMas (){ /// POR CONVENIO LOS NOMBRES DE LOS MÉTODOS EN JAVA SE PONEN QUE EMPIECEN POR MINÚSCULA
		if(this.getValor()==10){
			return(new Carta(0,-1));  //En el caso de que no pueda haber una carta mayor (caso del rey)
		}							//Devuelvo un valor de carta que no pueda aparecer
		else{
			return(new Carta(this.getValor()+1,this.getPalo()));
		}
	}
	
	public Carta CartaMenos (){ /// POR CONVENIO LOS NOMBRES DE LOS MÉTODOS EN JAVA SE PONEN QUE EMPIECEN POR MINÚSCULA
		if(this.getValor()==1){
			return(new Carta(0,-1));
		}
		else{
			return(new Carta(this.getValor()-1,this.getPalo()));
		}
	}
	
	public boolean equals (Carta c){
		return((this.getValor()==c.getValor())&&(this.getPalo()==c.getPalo()));
	}
	
}
/// OK