import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GrafoConColores extends Grafo {

	private Map<Integer,Integer> grafoColor;
	
	public GrafoConColores(){
		super();
		this.grafoColor= new Hashtable<Integer,Integer>();
	}
	
	public void setVertexColor(Integer n, Integer color){
		this.grafoColor.put(n, color);
	}
	
	public void removeVertexColor (Integer n){
		this.grafoColor.remove(n);
	}
	
	public int getVertexColor (Integer n){
		return this.grafoColor.get(n);
	}
	
	public void removeAll(){
		this.grafoColor.clear();
	}
	
	public List<Integer> verticesColored(){
		List<Integer> list= new ArrayList<Integer>();
		for(int n: this.grafoColor.keySet()){
			list.add(n);
		}
		return list;
	}
	
	public boolean validcolor (Integer n, Integer color){
		boolean validcolor=true;
		for(int i: super.verticesAdjacent(n)){
			try{
				if(color==this.getVertexColor(i)){
					return false;
				}
			}
			catch(NullPointerException e){
				validcolor=true; // Esta parte del código no sirve para nada, ya que es true por defecto, pero representa la idea de que un vertice no tenga color
			}
		}
		return validcolor;
	}
	
	public boolean color(int numcolors){
		List<Integer> listVertex;	// lista auxiliar en la que colocaré todos los vértices
		 
		/* Para poder aplicar el algoritmo de coloración de un grafo necesito tener los
		vértices almacenados en orden. En primer lugar colocaré los vértices que
		tienen ya un color asignado (este color no podrá modificarse). A
		continuación colocaré en la lista el resto de vértices, a los que el algoritmo
		de coloración irá asignando diferentes colores hasta dar con una
		combinación correcta.
		*/
		 
		List<Integer> listVertexcolored=this.verticesColored();
		List<Integer> listVerticesNoncolored= super.vertices(); //todos
		listVerticesNoncolored.removeAll(listVertexcolored); //quito los coloreados


		//Compruebo que los colores que ya están asignados son correctos
		 
		for(int v:listVertexcolored){
			if (!this.validcolor(v, this.getVertexColor(v))){
				return false;
			}
				 
		}
		 
		// vuelco los vértices en la nueva lista, en el orden correcto
		 
		listVertex=new ArrayList<Integer>( );
		listVertex.addAll(listVertexcolored);
		listVertex.addAll(listVerticesNoncolored);
		int k=listVertexcolored.size( );
		boolean b=this.coloreoConRetroceso(listVertex, k, numcolors);
		 
		if (b == false) {
			// no se ha podido colorear el grafo
			// vuelvo a la situación inicial
			for (int i = 0; i < listVerticesNoncolored.size( ); i++) {
				this.grafoColor.remove(listVerticesNoncolored.get(i));
			}
		}
		 
		return b;
		 
	}
	
	private boolean aceptable(List<Integer> listVertex, int color, int posicion){
		/*
		devuelve true si al vértice que ocupa la posición k en listVertex
		puedo asignarle el color k de modo que no haya ningún vértice en las
		posiciones anteriores que sea adyacente y que tenga el mismo color asignado.
		*/
		boolean acept=true;
		for (int i=0; i<posicion && acept; i++){
			if (super.areAdjacent(listVertex.get(i), listVertex.get(posicion)) && this.getVertexColor(listVertex.get(i))== color){
				acept=false;
			}
		}
		return acept;
	}
	
	private boolean coloreoConRetroceso(List<Integer> listaVertices, int k, int numColores){
		/*
		Supongo que a los vértices situados en las posiciones 0..k-1
		de listaVertices ya les he asignado color.
		Busco un color para el vértice en la posición k que sea compatible
		con los anteriores.
		*/
		if (k==listaVertices.size( )){
			return true;
		}
		else {
			for (int c=1; c<=numColores; c++){
				if (this.aceptable(listaVertices,c, k)) {
					this.grafoColor.put(listaVertices.get(k), c);
					boolean b=coloreoConRetroceso(listaVertices,k + 1, numColores);
					if (b)
						return b;
				}
			}
		}
		// he recorrido todas las combinaciones y ninguna es válida, devuelvo falso.
		return false;
	}
	
	
}
