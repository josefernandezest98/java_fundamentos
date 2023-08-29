import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Hashtable;

public class Grafo {

	private Map<Integer,List<Integer>> grafo;
	
	public Grafo(){
		grafo = new Hashtable <Integer,List<Integer>>();
	}
	
	public void addVertex(Integer n){
		this.grafo.put(n, new ArrayList<Integer>());
	}
	
	public void addEdge (Integer n1, Integer n2){
		if(!this.areAdjacent(n1,n2)){
			this.grafo.get(n1).add(n2);
			this.grafo.get(n2).add(n1);
		}
	}
	
	public boolean areAdjacent(Integer n1, Integer n2){
		return this.grafo.get(n1).contains(n2);
	}
	
	public int numVertex(){
		return this.grafo.size();
	}
	
	public boolean isVertex (Integer n){
		return this.grafo.containsKey(n);
	}
	
	public List<Integer> vertices(){
		List<Integer> list= new ArrayList<Integer>();
		for(int n: this.grafo.keySet()){
			list.add(n);
		}
		return list;
	}

	public List<Integer> verticesAdjacent(Integer n){
		return this.grafo.get(n);
	}
	
	public void removeEdge(Integer n1, Integer n2){
		if(this.areAdjacent(n1, n2)){
			this.grafo.get(n1).remove(n2);
			this.grafo.get(n2).remove(n1);
		}
	}
	
	public void removeVertex(Integer n){
		int i = 0;
		List<Integer> list= new ArrayList<Integer>(this.verticesAdjacent(n)); //Creo una lista auxiliar que luego recorreré
		int size = list.size();
		
		//Recorro la lista auxiliar eliminando todas las aristas entre vertices comunes
		while(i<size){
			this.removeEdge(n,list.get(i));
			i++;
		}
		
		//Elimino la entrada del valor n
		this.grafo.remove(n);
	}
	
	
	
	
	
}
