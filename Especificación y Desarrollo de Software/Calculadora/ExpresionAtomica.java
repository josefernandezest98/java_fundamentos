
public class ExpresionAtomica extends Expresion {
	
	private int valor;
	
	public ExpresionAtomica (int n){
		super();
		this.valor=n;	
	}
	
	public ExpresionAtomica (String var){
		super();
		this.variables.add(var);
	}

	public int calcula(Valoracion val) {
		if(this.variables.isEmpty()){
			return this.valor;
		}
		else{
			return val.valorVariable(this.variables.get(0));
		}
	}

	public void sustituir(String var, int n) {
		if(this.estaVariable(var)){
			this.valor=n;
			this.variables.remove(0);
		}
	}

	public void renombrar(String vieja, String nueva) {
		if(this.estaVariable(vieja)){
			this.variables.set(0, nueva);
		}
	}

	public Expresion copiar() {
		Expresion e;
		if(this.variables.isEmpty()){
			e = new ExpresionAtomica(this.valor);
		}
		else{
			e = new ExpresionAtomica(this.variables.get(0));
		}
		return e;
		
	}
	
	public String toString(){
		if(this.variables.isEmpty()){
			return Integer.toString(this.valor);
		}
		else{
			return this.variables.get(0);
		}
	}
	
	
	

}
