package Ejercicio1_AdivinarNumero;

import java.util.Random;

public class Arbitro {
	private static final Random RANDOM=new Random();
	private int azar;

	private int turnoJugador;
	
	private int numTotalJugadores;
	
	private boolean juegoAcabado=false;
	
	private Jugador jugador;

	
	public Arbitro(int totalJugadores) {
		this.numTotalJugadores=totalJugadores;
		this.azar=(int)RANDOM.nextLong(1,11);
		this.juegoAcabado=false;
		turnoJugador++;
	}

	private int getAzar() {
		return azar;
	}
	private int getTurnoJugador() {
		return turnoJugador;
	}
	
	//no se comprobara el numero de otro numero hasta que termine el que esta utilizando
	public synchronized boolean comprobarNumero(Jugador jugador, int numero) {
		boolean bandera=false;
		if(idJugadorCorrecto(jugador)) {
			if(numero==azar) {
				bandera=true;
			}
		}
		return bandera;
	}
	
	private boolean idJugadorCorrecto(Jugador jugador) {
		boolean bandera=false;
		if(jugador.getIdJugador()==turnoJugador) {
			bandera=true;
		}
		return bandera;
	}
}


