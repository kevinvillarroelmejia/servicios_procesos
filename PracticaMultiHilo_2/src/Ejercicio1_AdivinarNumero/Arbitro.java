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
		System.out.println("Numero a adivinar: " + azar);

		this.juegoAcabado = false;
		turno++;
	}

	// no se comprobara el numero de otro numero hasta que termine el que esta
	// utilizando
	// por que es synchronized
	public synchronized void comprobarNumeroAzar(Jugador jugador, int numeroJugado) {

		System.out.println("Jugador " + jugador.getIdJugador() + " dice: " + numeroJugado);
		if (numeroJugado == azar) {
			juegoAcabado = true;
			System.out.println("Jugador " + jugador.getIdJugador() + ", adivino el numero!!!");
		} else {
			turno = turno % numTotalJugadores + 1;
			System.out.println("Le toca a Jug" + jugador.getIdJugador());

		}
	}

	// synchronized --> Así cada hilo lee el valor actualizado del turno y de si se
	public synchronized boolean idJugadorCorrecto(Jugador jugador) {
		boolean bandera = false;
		if (jugador.getIdJugador() == turno) {
			bandera = true;
		}
		return bandera;
	}
<<<<<<< HEAD
	public int getAzar() {
		return azar;
	}
=======

	public boolean isJuegoAcabado() {
		return juegoAcabado;
	}

>>>>>>> branch 'master' of https://github.com/kevinrashiid/servicios_procesos.git
}
