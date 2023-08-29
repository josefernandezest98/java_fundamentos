
public class DobleCola<T> {

	class Nodo<X>{
		private X dato;
		private Nodo<X> sig;
		private Nodo<X> ant;
		private Nodo(X t){
			this.dato=t;
			this.sig=null;
			this.ant=null;	
		}
	}
	
	private Nodo<T> primero;
	private Nodo<T> ultimo;
	
	public DobleCola(){
		this.primero=null;
		this.ultimo=null;
	}
	
	public boolean vacia(){
		return(this.primero==null);
	}
	public T getPrimero(){
		return this.primero.dato;
	}
	public T getUltimo(){
		return this.ultimo.dato;
	}
	
	public void anadirPrincipio(T elem){
		Nodo<T> aux= new Nodo<>(elem);
		if(this.vacia()){
			this.primero=aux;
			this.ultimo=aux;
		}
		else{
			this.primero.sig=aux; /// DEBERÍA SER this.primero.ant=aux??
			aux.ant=this.primero;
			this.primero=aux;
		}
	}
	
	public void anadirFinal(T elem){
		Nodo<T> aux= new Nodo<>(elem);
		if(this.vacia()){
			this.primero=aux;
			this.ultimo=aux;
		}
		else{
			this.ultimo.ant=aux; /// DEBERÍA SER this.ultimo.sig=aux??
			aux.sig=this.ultimo;
			this.ultimo=aux;
		}
	}
	
	public void eliminarPrincipio(){
		if(this.primero==this.ultimo){//Si primero y ultimo apuntan al mismo lugar de memoria significa que solo hay un elemento o es nulo
			this.primero=null;
			this.ultimo=null;
		}
		else{
			this.primero=this.primero.ant;  /// DEBERÍA SER this.primero=this.primero.sig??
			this.primero.sig.ant=null;
			this.primero.sig=null;
		}
	}
	public void eliminarFinal(){
		if(this.primero==this.ultimo){//Si primero y ultimo apuntan al mismo lugar de memoria significa que solo hay un elemento o es nulo
			this.primero=null;
			this.ultimo=null;
		}
		else{
			this.ultimo=this.ultimo.sig; 
			this.ultimo.ant.sig=null;
			this.ultimo.ant=null;
		}
	}
	
	public void mostrar(){
		Nodo<T> aux= this.primero;
		while(aux!=null){
			System.out.println(aux.dato.toString());
			aux=aux.ant;
		}	
	}
}
/// FUNCIONA BIEN PERO ESTÁN "INTERCAMBIADOS" primero, ultimo, principio y final (SIGNIFICAN LO CONTRARIO...)
