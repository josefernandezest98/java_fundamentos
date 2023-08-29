
public abstract class ExpresionBinaria extends Expresion{
	
	protected Expresion e1;
	protected Expresion e2;
	
	protected ExpresionBinaria(Expresion e1, Expresion e2){
		super();
		this.e1=e1.copiar();
		this.e2=e2.copiar();
		this.variables.addAll(e1.listaVariables());
		for(String var: e2.listaVariables()){
			if(!this.variables.contains(var)){
				this.variables.add(var);
			}
		}
	}
	
	public void sustituir(String var, int n) {
		if(this.estaVariable(var)){
			this.variables.remove(var);
			this.e1.sustituir(var, n);
			this.e2.sustituir(var, n);
		}
	}
	
	public void renombrar(String vieja, String nueva){
		if(this.estaVariable(vieja)){
			if(this.estaVariable(nueva)){
				this.variables.remove(vieja);
			}
			else{
				this.variables.set(this.variables.indexOf(vieja), nueva);
			}
			this.e1.renombrar(vieja, nueva);
			this.e2.renombrar(vieja, nueva);
		}
	}
		
}
