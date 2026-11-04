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
		int azar=(int) RANDOM.nextLong(1, 11);
		while (!arbitro.comprobarNumeroAzar(this, azar)){
			if (arbitro.idJugadorCorrecto(this)) {
				
			}
		}
	}
}
