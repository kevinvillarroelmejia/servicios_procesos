package Ejercicio1_AdivinarNumero;

import java.util.Random;

public class Arbitro {
	private static final Random RANDOM = new Random();

	// TODOS LOS JUGADORES COMPARTE ESTA CLASE

	private int azar; // numero a adivinar
	private int turno;// turno del jugador
	private int numTotalJugadores;
	private boolean juegoAcabado = false;

	public Arbitro(int totalJugadores) {
		this.numTotalJugadores = totalJugadores;
		this.azar = (int) RANDOM.nextLong(1, 11);
		this.juegoAcabado = false;
		turno++;
	}


	// no se comprobara el numero de otro numero hasta que termine el que esta
	// utilizando
	// por que es synchronized
	public synchronized boolean comprobarNumeroAzar(Jugador jugador, int numeroJugado) {
		boolean bandera = false;
		System.out.println("Jugador " + jugador.getIdJugador() + " dice: " + numeroJugado);
		if (numeroJugado == azar) {
			bandera = true;
			System.out.println("Jugador " + jugador.getIdJugador() + ", adivino el numero!!!");
		}
		return bandera;
	}

	// ¿el idJugador es igual que el turno?
	public boolean idJugadorCorrecto(Jugador jugador) {
		boolean bandera = false;
		if (jugador.getIdJugador() == turno) {
			bandera = true;
		}
		return bandera;
	}
}
