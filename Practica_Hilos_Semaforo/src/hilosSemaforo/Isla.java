package hilosSemaforo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Isla {
	private static final Random RANDOM = new Random();

	private int numNaufragos = (int) RANDOM.nextLong(800, 1001); // entre 800 y 1000 Naufragos
	private Barca barca;

	Semaphore semaforo;

	public Isla(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	public int getNumNaufragos() {
		return numNaufragos;
	}

	public boolean rescatarNaufragos() {
		boolean bandera=false;
		try {
			System.out.println("La balsa actual es la Balsa " + barca.getIdBarca());
			this.semaforo.acquire();
			synchronized (this) {
				if (numNaufragos > 15) {
					numNaufragos = numNaufragos - 15;
					bandera=true;
				} else if (numNaufragos < 15) {
					numNaufragos = numNaufragos - numNaufragos;
					bandera=true;
				} else if (numNaufragos == 0) {
					bandera=false;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bandera;
	}

}
