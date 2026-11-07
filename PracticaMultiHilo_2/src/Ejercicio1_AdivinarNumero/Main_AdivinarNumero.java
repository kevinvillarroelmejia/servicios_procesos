package Ejercicio1_AdivinarNumero;

public class Main_AdivinarNumero {

	public static void main(String[] args) {

		Arbitro arbitro=new Arbitro(3);
		
		Jugador player1=new Jugador(1, arbitro);
		Jugador player2=new Jugador(2, arbitro);
		Jugador player3=new Jugador(3, arbitro);
		
		player3.start();
		player1.start();
		player2.start();
		
	}

}
