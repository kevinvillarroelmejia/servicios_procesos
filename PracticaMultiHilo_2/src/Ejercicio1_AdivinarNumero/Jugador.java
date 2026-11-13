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

			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
