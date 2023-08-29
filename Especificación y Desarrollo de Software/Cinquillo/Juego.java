import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
	
	public static void main(String[] args) {
		
		int jugadores=0;
		int humanos=0;
		boolean exception;
		Scanner ent = new Scanner(System.in);
		
		System.out.println("Bienvenido a la partida del cinquillo:");
		do{
		System.out.println("Elija el numero de jugadores totales de la partida, recuerda que tiene que ser un numero entre 2 y 6:");
		try
		{
			exception=false;
			jugadores= ent.nextInt();
		}
		catch(InputMismatchException e)
		{
			exception=true;
		}
		finally{
			ent.nextLine();
		}
		}
		while(exception || jugadores>6 || jugadores<2);
		do{
			System.out.println("Elija el numero de jugadores humanos de la partida, recuerde introducir un numero menor que el de jugadores totales:");
			try
			{
				exception=false;
				humanos= ent.nextInt();
			}
			catch(InputMismatchException e)
			{
				exception=true;
			}
			finally{
				ent.nextLine();
			}
			}
			while(humanos>jugadores || exception);
		
		

		Partida Cinquillo = new Partida(humanos,jugadores-humanos);
		Cinquillo.iniciarPartida();
		
		while(Cinquillo.finalizado()==false){
			Cinquillo.jugada();
		}
		
		Cinquillo.clasificacion();
	}
}
