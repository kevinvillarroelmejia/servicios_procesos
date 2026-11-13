package Ejercicio1_AdivinarNumero;

import java.util.Random;

public class Jugador extends Thread {
	private static final Random RANDOM = new Random();

	private int idJugador;
	private Arbitro arbitro;

	public Jugador(int idJudador, Arbitro arbitro) {
		this.idJugador = idJudador;
		this.arbitro = arbitro;
		arbitro.idJugadorCorrecto(this);// comprobadmos si le toca
	}

	public int getIdJugador() {
		return idJugador;
	}

	@Override
	public void run() {
<<<<<<< HEAD
		boolean bandera = false;
		System.out.println("NUMERO A ADIVINAR: " + arbitro.getAzar());
		try {
			while (!bandera) {
				if (arbitro.idJugadorCorrecto(this)) {
					Thread.sleep(100);
					int azarJugador = (int) RANDOM.nextLong(1, 11);
					if (arbitro.comprobarNumeroAzar(this, azarJugador)) {
						bandera = true;
					}
				}

=======
		try {
		while (!arbitro.isJuegoAcabado()) { //comprueba si el anterior hilo termino el juego
			if (arbitro.idJugadorCorrecto(this)) {//comprueba si es el turno del hilo que coge run
					Thread.sleep(100);
					int numIntento = (int) RANDOM.nextLong(1, 11); //intento de cada jugador
					arbitro.comprobarNumeroAzar(this, numIntento);
				}
>>>>>>> branch 'master' of https://github.com/kevinrashiid/servicios_procesos.git
			}
<<<<<<< HEAD
		} catch (Exception e) {
			e.getStackTrace();
=======
		}catch (Exception e) {
			e.getMessage();
>>>>>>> branch 'master' of https://github.com/kevinrashiid/servicios_procesos.git
		}
	}
}
