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
			this.semaforo.acquire();
			synchronized (this) {
				if (numNaufragos > 15) {
					numNaufragos = numNaufragos - 15;
					System.out.println("La balsa actual es "+barca.getIdBarca()+" y rescatamos a 15");
					bandera=true;
				} else if (numNaufragos < 15) {
					numNaufragos = numNaufragos - numNaufragos;
					System.out.println("La balsa actual es "+barca.getIdBarca()+" y rescatamos a "+numNaufragos);
					bandera=true;
				} else if (numNaufragos == 0) {
					System.out.println("La balsa actual es "+barca.getIdBarca()+" y rescatamos a "+numNaufragos);
					bandera=false;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bandera;
	}

}
