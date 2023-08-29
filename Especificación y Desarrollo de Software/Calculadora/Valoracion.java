import java.util.Hashtable;
import java.util.Map;

public class Valoracion {
	
	private Map<String,Integer> valores;
	
	public Valoracion(){
		valores = new Hashtable<String,Integer>();
	}
	
	public void anadirVariable(String var,int n){
		this.valores.put(var, n);
	}
	
	public void eliminarVariable(String var){
		this.valores.remove(var);
	}
	
	public void modificarVariable(String var, int n){
		this.valores.replace(var, n);
	}
	
	public int valorVariable(String var){
		if(this.estaValorada(var)){
			return this.valores.get(var);
		}
			return 0;
	}
	
	public boolean estaValorada (String var){
		return this.valores.containsKey(var);
	}
	
	public boolean coherentes(Valoracion v){
		for(String s: this.valores.keySet()){
			if(v.estaValorada(s)){
				if(this.valorVariable(s)!=v.valorVariable(s)){
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
}
