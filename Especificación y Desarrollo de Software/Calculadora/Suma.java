
public class Suma extends ExpresionBinaria{
	
	public Suma(Expresion e1, Expresion e2){
		super(e1,e2);
	}
	
	
	public int calcula(Valoracion v) {
		return this.e1.calcula(v)+this.e2.calcula(v);
	}

	public Expresion copiar() {
			Expresion e;
			e= new Suma(this.e1.copiar(),this.e2.copiar());
			return e;
		}
	
	public String toString(){
		return "(" + this.e1.toString()+ "+" + this.e2.toString() + ")";
	}
	
}

