
public class ExpresionUnaria extends Expresion{

	private Expresion e;
	
	public ExpresionUnaria(Expresion e){
		super();
		this.e=e.copiar();
		this.variables.addAll(e.listaVariables());
	}
	
	public int calcula(Valoracion v) {
		return -(this.e.calcula(v));
	}

	public void sustituir(String var, int n) {
		if(this.estaVariable(var)){
			this.variables.remove(var);
			this.e.sustituir(var, n);
		}
	}

	public void renombrar(String vieja, String nueva) {
		if(this.estaVariable(vieja)){
			if(this.estaVariable(nueva)){
				this.variables.remove(vieja);
			}
			else{
				this.variables.set(this.variables.indexOf(vieja), nueva);
			}
			this.e.renombrar(vieja, nueva);
		}
	}

	public Expresion copiar() {
		Expresion e;
		e= new ExpresionUnaria(this.e.copiar());
		return e;
	}
	
	public String toString(){
		return "(-"+this.e.toString()+")";
	}
	
	
}
