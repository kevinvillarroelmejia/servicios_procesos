package cadenaMontaje;

import java.util.Random;

public class colocador_HILO extends Thread {
	private static final Random RANDOM = new Random();
	private Estanteria_Comun estanteria;
	private int contador = 0;
	private boolean terminoColocador = false;

	public colocador_HILO(Estanteria_Comun estanteria) {
		this.estanteria = estanteria;
	}

	@Override
	public void run() {
		for (int i = 0; i <= 20; i++) {
			int numeroColocar = (int) RANDOM.nextLong(1, 4);// del 1 al 3
			System.out.println("Produciendo tipo " + numeroColocar);
			estanteria.colocar(numeroColocar);
			try {
				sleep(250);
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

}
