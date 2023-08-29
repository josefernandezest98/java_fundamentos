import java.util.Random;
import java.util.Scanner;

public class Principal {

	private static Scanner ent=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int option=0;
		int numFilas=0;
		boolean repeat =false;
		
		
		System.out.println("Bienvenido al apasionante juego del Sudoku");
		System.out.println("Elija que desea hacer");
		System.out.println("1-Jugar");
		System.out.println("2-Introduce un sudoku y lo resolveremos automaticamente");
		do{
			try{
				System.out.println("Introduzca la opcion 1 o 2");
				option =ent.nextInt();
				repeat=(option<1)||(option>2);
			}
			catch(Exception e){
				ent.nextLine();
				repeat=true;
			}	
		}
		while(repeat==true);
		
		switch(option){
		
			case 1:
			{
				System.out.println("Ahora vamos a crear un Sudoku randomizado para usted:");
				System.out.println("Introduzca el numero de filas del Sudoku");
				do{
					try{
						System.out.println("Recuerde que en esta version solo disponemos de tamaños 4 y 9");
						numFilas =ent.nextInt();
						repeat=(numFilas!=4)&&(numFilas!=9);
					}
					catch(Exception e){
						ent.nextLine();
						repeat=true;
					}	
				}
				while(repeat==true);
				
				int[][] matriz = new int[numFilas][numFilas];
				SudokuConSolucion Sudoku = new SudokuConSolucion(matriz);
				int numPistas=(numFilas*numFilas)/3; //Para el 4x4 serían 5 pistas y para el 9x9 serian 27
				int valor;
				int fila=0; 
				int columna=0; //Las inicializamos para el caso 2
				
				//Creacion del Sudoku Randomizado
				
				Random r = new Random();
				
				for(int i=0;i<numPistas;i++){
					
					//Primero encontramos una casilla que este libre
					
					do{
						fila=r.nextInt(numFilas);
						columna=r.nextInt(numFilas);
					}
					while(Sudoku.getSquare(fila,columna)!=0);
					
					//Después buscamos un valor para dicha casilla
					do{
						repeat=false;
						valor=r.nextInt(numFilas)+1;
						Sudoku.setSquareInitialValue(fila, columna, valor); //Aqui puede no añadirlo si ese valor no se puede poner entonces es necesario verlo en el if
						if(Sudoku.getSquare(fila,columna)==0){
							repeat=true;
						}
						else{
							if(!Sudoku.isSolvable()){
								Sudoku.removeInitialSquare(fila, columna);
								repeat=true;
							}
						}
					}
					while(repeat);
				}
				
				//Comienza el juego del sudoku
				
				do{
					Sudoku.show();
					
					System.out.println();
					System.out.println("Elija que desea hacer: ");
					System.out.println("1-Introducir un dato");
					System.out.println("2-Eliminar un dato");
					System.out.println("3-Resetear el Sudoku");
					System.out.println("4-Comprobar si tienes errores");
					System.out.println("5-Acabar y corregir");
					System.out.println();
					
					do{
						try{
							System.out.println("Introduzca una opcion del 1 al 5");
							option =ent.nextInt();
							repeat=((option<1)||(option>5));
						}
						catch(Exception e){
							ent.nextLine();
							repeat=true;
						}
					}
					while(repeat);
					
					
					switch(option){
					
					case 1:
						introducirNum(Sudoku);
						break;
						
					case 2:
						
						//Introducimos el valor de la fila
						System.out.println("Introduzca la fila donde quiere eliminar el valor");
						do{
							try{
								System.out.println("Introduzca un numero entre 0 y " + (Sudoku.numRows()-1));
								fila =ent.nextInt();
								repeat=(fila<0)||(fila>(Sudoku.numRows()-1));
							}
							catch(Exception e){
								ent.nextLine();
								repeat=true;
							}	
						}
						while(repeat);
						
						//Introducimos el valor de la columna
						System.out.println("Introduzca la columna donde quiere eliminar el valor");
						do{
							try{
								System.out.println("Introduzca un numero entre 0 y " + (Sudoku.numRows()-1));
								columna =ent.nextInt();
								repeat=(columna<0)||(columna>(Sudoku.numRows()-1));
							}
							catch(Exception e){
								ent.nextLine();
								repeat=true;
							}	
						}
						while(repeat);
						
						if(Sudoku.getSquareInitialValue(fila, columna)!=0){
							System.out.println("Este valor es un valor inicial y no puede ser modificado");
						}
						else{
							Sudoku.removeSquare(fila, columna);//Eliminamos el valor al Sudoku 
							System.out.println("Se ha eliminado el valor de la casilla" + "["+fila+"]"+"["+columna+"]" );
						}
						

						
						break;
						
					case 3:
						
						Sudoku.reset();
						
						System.out.println("El Sudoku ha sido reseteado");
						
						break;
						
					case 4:
						
						if(Sudoku.isSolvable()){
							System.out.println("No tienes errores");
						}
						else{
							System.out.println("Tienes errores");
						}
						
						break;
					
					}
					
					
				}
				while(option!=5);
				
				System.out.println("Ha decidido acabar para que le corrijamos el Sudoku");
				if(!Sudoku.isSolvable()){
					System.out.println("Lo siento amigo, no ha sido capaz de acabar el Sudoku correctamente ");
				}
				else{
					if(Sudoku.completeSudoku()){
						System.out.println("Enhorabuena ha finalizado el Sudoku correctamente");
					}
					else{
						System.out.println("Lo siento amigo, no ha sido capaz de acabar el Sudoku correctamente ");
					}
				}
		}	
			break;
				
			case 2:
				System.out.println("Introduzca el numero de filas del Sudoku");
				do{
					try{
						System.out.println("Recuerde que en esta version solo disponemos de tamaños 4 y 9");
						numFilas =ent.nextInt();
						repeat=(numFilas!=4)&&(numFilas!=9);
					}
					catch(Exception e){
						ent.nextLine();
						repeat=true;
					}	
				}
				while(repeat);
				
				int[][] matriz2 = new int[numFilas][numFilas];
				SudokuConSolucion Sudoku2 = new SudokuConSolucion(matriz2);
				
				System.out.println("Ahora introduzca los distintos valores del sudoku ");
				while(introducirNum(Sudoku2)){
					System.out.println();
					Sudoku2.show();
					System.out.println();
				};
				
				
				
				if(Sudoku2.isSolvable()){
					System.out.println("La solución del sudoku es:");
					Sudoku2.solve();
					Sudoku2.show();
				}
				else{
					System.out.println("Este sudoku no tiene solución");
				}
			
			break;
		
		}
		
		
		
	}
	
	public static boolean introducirNum(Sudoku Sudoku){
		
		int fila=0;
		int columna=0;
		int valor=0;
		int cambio=0;
		//Inicializo las tres variables porque si no al estar dentro de un try/catch igual no estan inicializadas.
		
		boolean repeat;
		
		//Introducimos el valor de la casilla
		System.out.print("Introduzca el valor deseado: ");
		do{
			try{
				System.out.println("Introduzca un numero entre 1 y " + Sudoku.numRows() + " si desea introducir un nuevo dato");
				System.out.println("Introduzca un 0 para no introducir ningun dato");
				valor =ent.nextInt();
				repeat=(valor<0)||(valor>Sudoku.numRows());
			}
			catch(Exception e){
				ent.nextLine();
				repeat=true;
			}	
		}
		while(repeat);
		
		if(valor!=0){
			
			//Introducimos el valor de la fila
			System.out.println("Introduzca la fila donde quiere introducir el valor");
			do{
				try{
					System.out.println("Introduzca un numero entre 0 y " + (Sudoku.numRows()-1));
					fila =ent.nextInt();
					repeat=(fila<0)||(fila>(Sudoku.numRows()-1));
				}
				catch(Exception e){
					ent.nextLine();
					repeat=true;
				}	
			}
			while(repeat);
			
			//Introducimos el valor de la columna
			System.out.println("Introduzca la columna donde quiere introducir el valor");
			do{
				try{
					System.out.println("Introduzca un numero entre 0 y " + (Sudoku.numRows()-1));
					columna =ent.nextInt();
					repeat=(columna<0)||(columna>(Sudoku.numRows()-1));
				}
				catch(Exception e){
					ent.nextLine();
					repeat=true;
				}	
			}
			while(repeat);
			
			if(Sudoku.getSquareInitialValue(fila, columna)!=0){
				System.out.println("Este valor es un valor inicial y no puede ser modificado");
			}
			else{
				if(Sudoku.getSquare(fila, columna)!=0){
					System.out.println("Esta casilla ya tiene un valor, ¿desea cambiar el valor " + Sudoku.getSquare(fila, columna) +" por el valor " + valor + "?");
					
					do{
						try{
							System.out.println("0-No");
							System.out.println("1-Si");
							cambio =ent.nextInt();
							repeat=(cambio<0)||(cambio>1);
						}
						catch(Exception e){
							ent.nextLine();
							repeat=true;
						}	
					}
					while(repeat);
					
					if(cambio==0){
						System.out.println("El sudoku no se ha modificado");
					}
					else{
						Sudoku.setSquare(fila, columna, valor);//Añadimos el valor al Sudoku (en el caso 2, del auto solucionador de sudokus,debería ser setSquareInitialValue() pero como no se va a hacer ningún reset no es necesario)
						if(Sudoku.getSquare(fila, columna)==valor){
							System.out.println("Se ha añadido el valor: " + valor + " en la casilla " + "["+fila+"]"+"["+columna+"]" );
						}
						else{
							System.out.println("No se ha añadido el valor: " + valor + " porque no se podía poner");
						}
						
					}
				}
				else{
					Sudoku.setSquare(fila, columna, valor);//Añadimos el valor al Sudoku (en el caso 2, del auto solucionador de sudokus,debería ser setSquareInitialValue() pero como no se va a hacer ningún reset no es necesario)
					if(Sudoku.getSquare(fila, columna)==valor){
						System.out.println("Se ha añadido el valor: " + valor + " en la casilla " + "["+fila+"]"+"["+columna+"]" );
					}
					else{
						System.out.println("No se ha añadido el valor: " + valor + " porque no se podía poner");
					}
				}
			}
			

			return true;
		}
		
		else{
			return false;
		}
	}
	
}
