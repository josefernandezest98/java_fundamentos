
public class Calculadora {

	private Expresion e;
	private Valoracion val;
	
	public Calculadora(Expresion e, Valoracion val){
		this.e=e;
		this.val=val;
	}
	
	public int calcular(){
			return this.e.calcula(this.val);
	}
	
	public void modificarExpresion(Expresion e){
		this.e=e;
	}
	
	public void anadirValoracion(String var, int n){
		this.val.anadirVariable(var, n);
	}
	
	public void eliminarValoracion(String var){
		this.val.eliminarVariable(var);
	}
	
	public void modificarVariable(String var, int n){
		this.val.modificarVariable(var, n);
	}
	
	public boolean calculable(){
		return this.e.calculable(this.val);
	}
	
	public void sustituirValores(){
		int numVar= this.e.listaVariables().size();
		for(int i=0;i<numVar;i++){
			this.e.sustituir(this.e.listaVariables().get(0),this.val.valorVariable(this.e.listaVariables().get(0)));
		}
	}
	
	public void mostrar(){
		System.out.println(this.e.toString());
	}
	
	public String toString(){
		return this.e.toString();
	}
	
	
}
