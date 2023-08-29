
public class SudokuConSolucion extends Sudoku{
	
	private GrafoConColores GrafoAsociado;
	
	public SudokuConSolucion(int[][] matInicial){
		super(matInicial);
		this.GrafoAsociado= new GrafoConColores();
		this.construirGrafoInicial();	
	}
	
	public boolean isSolvable(){
		boolean solvable = this.GrafoAsociado.color(this.numRows());
		if(solvable==true){ //Si es verdad, como ha sido coloreado tenemos que eliminar dicha coloración
			for(int i=0; i<this.numRows() ;i++){
				for(int j=0; j<this.numRows() ;j++){
					if(this.getSquare(i,j)==0){
						int vertex=(this.numRows()*i)+j+1;
						this.GrafoAsociado.removeVertexColor(vertex);
					}
				}
			}
		}
		return solvable;
	}
	
	public void solve(){
		int auxRow,auxColumn;
		this.GrafoAsociado.color(this.numRows()); //Resolvemos el grafo para ahora recorrerlo
		for(int i=1;i<=this.numRows()*this.numRows();i++){
			auxRow=(i-1)/this.numRows();
			auxColumn=(i-1)%this.numRows();
			this.setSquare(auxRow, auxColumn, this.GrafoAsociado.getVertexColor(i));
		}
		
	}
	
	public void setSquare(int row, int column,int value){
		if(super.isPlaceable(row, column, value)){
			super.setSquare(row, column, value);
			int vertex=(this.numRows()*row)+column+1;
			this.GrafoAsociado.setVertexColor(vertex, value);
		}
	}
	
	public void removeSquare(int row, int column){
		super.removeSquare(row, column);
		int vertex=(this.numRows()*row)+column+1;
		this.GrafoAsociado.removeVertexColor(vertex);
	}
	
	public void reset(){
		for(int i=0; i<this.numRows() ;i++){
			for(int j=0; j<this.numRows() ;j++){
				if(!(this.getSquare(i,j)==this.getSquareInitialValue(i,j))){
					this.removeSquare(i,j);
				}
			}
		}
	}
	
	public void setSquareInitialValue(int row, int column,int value){
		if(super.isPlaceable(row, column, value)){
			super.setSquareInitialValue(row, column, value);
			int vertex=(this.numRows()*row)+column+1;
			this.GrafoAsociado.setVertexColor(vertex, value);
		}
	}
	
	public void removeInitialSquare(int row, int column){
		super.removeInitialSquare(row, column);
		int vertex=(this.numRows()*row)+column+1;
		this.GrafoAsociado.removeVertexColor(vertex);
	}
	
	private void construirGrafoInicial(){
		 int numFilas=this.numRows( );
		 int numVertices=numFilas*numFilas;
		 for (int v=1; v<=numVertices; v++)
			 this.GrafoAsociado.addVertex(v); //GrafoAsociado es el nombre del atributo de Sudoku que contiene el grafo con colores
		 
		 //Añado aristas para todas las parejas de vértices que están en la misma fila    
		 for (int i = 0; i < numFilas ; i++){
			 for (int j = 0; j < numFilas; j++){
				 for (int k = j + 1; k < numFilas ; k++){
					 int v1=numFilas*i + j+1;
					 int v2=numFilas*i + k+1;
					 this.GrafoAsociado.addEdge(v1,v2);
					 }
				 }
			 }      
		 
		 //Añado aristas para todas las parejas de vértices que están en la misma columna
		 for (int j = 0; j < numFilas; j++){
			 for (int i = 0; i < numFilas ; i++){
				 for (int k = i + 1; k < numFilas ; k++){
					 int v1=numFilas*i + j+1;
					 int v2=numFilas*k + j+1;
					 this.GrafoAsociado.addEdge(v1,v2);
					 }
				 }
			 }
		 
		 //Añado aristas para todas las parejas de vértices que están en la misma región
		 int n = (int)Math.sqrt(numFilas);
		 for (int i = 0; i < n ; i++){
			 for (int j = 0; j < n; j++){
				 int i0 = i * n;
				 int j0 = j * n;
				 // (i0,j0) es la esquina superior izquierda de la región
				 for (int i1 = i0; i1 < i0 + n; i1++){
					 for (int j1 = j0; j1 < j0 + n; j1++){
						 for (int i2 =i0; i2<i0+n; i2++){
							 for (int j2 = j0; j2 < j0 + n; j2++){
								 int v1 = numFilas * i1 + j1 + 1;
								 int v2 = numFilas * i2 + j2 + 1;
								 if ((v2 != v1) && !this.GrafoAsociado.areAdjacent(v1, v2)){
									 this.GrafoAsociado.addEdge(v1, v2);
								 }	 
							}
						}
					}
				}
			}
		}     
		 
		// Por último añado los colores a los vértices correspondientes a los valores iniciales del sudoku
		 for (int i=0; i<numFilas; i++){
			 for (int j=0; j<numFilas; j++){
				 if (this.getSquareInitialValue(i,j)!=0)
					 this.GrafoAsociado.setVertexColor(i*numFilas+j+1,this.getSquareInitialValue(i,j));
				 }
			 }  
								 
}

}
