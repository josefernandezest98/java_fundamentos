
public class Sudoku {

	private int size; //Tamaño n x n
	private int boxSize;
	private int[][] matriz;
	private int[][] matrizInicial;
	
	public Sudoku(int[][] matInicial){
		this.size=matInicial.length;
		this.boxSize=(int)Math.sqrt(size);
		this.matriz= new int[size][size];
		this.matrizInicial= new int[size][size];
		
		for(int i=0; i<this.size ;i++){
			for(int j=0; j<this.size ;j++){
				this.matriz[i][j]=matInicial[i][j];
				this.matrizInicial[i][j]=matInicial[i][j];
			}
		}
	}
	
	public int numRows(){
		return this.size;
	}
	
	public void setSquare(int row, int column,int value){
		this.matriz[row][column]=value;
	}
	
	public int getSquare(int row, int column){
		return this.matriz[row][column];
	}
	
	public void removeSquare(int row, int column){
		this.matriz[row][column]=0;
	}
	
	public int getSquareInitialValue(int row, int column){
		return this.matrizInicial[row][column];
	}
	
	public void setSquareInitialValue(int row, int column,int value){
		this.matriz[row][column]=value;
		this.matrizInicial[row][column]=value;
	}
	
	public void removeInitialSquare(int row, int column){
		this.matriz[row][column]=0;
		this.matrizInicial[row][column]=0;
	}
	
	public boolean completeSudoku(){ //Este metodo nos dice si todas las casillas del Sudoku tienen un valor distinto de 0
		for(int i=0; i<this.size ;i++){
			for(int j=0; j<this.size ;j++){
				if(this.matriz[i][j]==0){
					return false;
				}
			}
		}
		return true;
	}
	
	public void show(){
	
		for(int i=0; i<size ;i++){
			
			//Con este if creamos las lineas horizontales
			if(i%this.boxSize==0){
				for(int k=0;k<(this.size+1)*3;k++){
					System.out.print("-");
				}
				System.out.println("");
			}
			
			for(int j=0; j<size ;j++){
				if(j%this.boxSize==0){
					System.out.print("|");
				}
				if(this.matriz[i][j]==0){
					System.out.print("   ");
				}
				else{
					System.out.print(" "+this.matriz[i][j]+" ");
				}
				
			}
			System.out.println("|");
		}
		for(int k=0;k<(this.size+1)*3;k++){
			System.out.print("-");
		}
		System.out.println("");
	}
	
	public void reset(){
		for(int i=0; i<size ;i++){
			for(int j=0; j<size ;j++){
				this.matriz[i][j]=this.matrizInicial[i][j];
			}
		}
	}
	
	public boolean isPlaceable (int row, int column, int value){
		
		//Compruebo primero que se puede poner en su cuadrante
		
		int auxRow = boxSize*(row/boxSize); //Irá a la fila donde empieza su cuadrante
		int auxColumn = boxSize*(column/boxSize); //Irá a la columna donde empieza su cuadrante
		
		for(int i=0;i<boxSize;i++){
			for(int j=0;j<boxSize;j++){
				if(value==this.getSquare(auxRow+i,auxColumn+j)){
					return false;
				}
			}
		}
		
		//Compruebo ahora la fila
		//Primero hasta que llegue al cuadrante donde pertenece
		for(int i=0;i<auxColumn;i++){
			if(value==this.getSquare(row,i)){
				return false;
			}
		}
		
		//Segundo hasta que llegue al final a partir del cuadrante donde pertenecia
		for(int i=auxColumn+boxSize;i<size;i++){
			if(value==this.getSquare(row,i)){
				return false;
			}
		}
		
		//Ahora compruebo la columna igual que antes
		for(int i=0;i<auxRow;i++){
			if(value==this.getSquare(i,column)){
				return false;
			}
		}
		
		for(int i=auxRow+boxSize;i<size;i++){
			if(value==this.getSquare(i,column)){
				return false;
			}
		}
		
		//Si no encuentra ningun inconveniente es verdad
		
		return true;
		
	}
}
