import java.util.ArrayList;
import java.util.List;

public abstract class Expresion {
	
	protected List<String> variables;
	
	protected Expresion(){
		this.variables=new ArrayList<String>();	
	}
	
	public int numVariables(){
		return this.variables.size();
	}
	
	public boolean estaVariable(String var){
		return this.variables.contains(var);
	}
	
	public boolean calculable (Valoracion v){
		for(String s: this.variables){
			if(!v.estaValorada(s)){
				return false;
			}
		}
		return true;
	}
	
	public List<String> listaVariables(){
		return this.variables;
	}
	
	public abstract int calcula(Valoracion v);
	
	public abstract void sustituir(String var,int n);
	
	public abstract void renombrar(String vieja, String nueva);
	
	public abstract Expresion copiar();
	
	public abstract String toString();
	
}
