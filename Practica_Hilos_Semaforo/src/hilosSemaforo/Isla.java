package hilosSemaforo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Isla {
	private static final Random RANDOM = new Random();

	private int numNaufragos = (int) RANDOM.nextLong(800, 1001); // entre 800 y 1000 Naufragos
	
	//cuando son menos de 15 los que quedan no sabemos 
	//cuantos son los que vamos a rescatar
	private int rescatadosNum;

	Semaphore semaforo;

	public Isla(Semaphore semaforo) {
		this.semaforo = semaforo;
		System.out.println("Total: "+numNaufragos);
	}

	public int getNumNaufragos() {
		return numNaufragos;
	}

	public int rescatarNaufragos() {
		int numeroRescatados=0;
		try {
			this.semaforo.acquire();
			//el synchronized siempre va en la clase comun
			synchronized (this) {
				if (numNaufragos >= 15) {
					numNaufragos = numNaufragos - 15;
					numeroRescatados=15;
				} else if (numNaufragos < 15) {
					numeroRescatados = numNaufragos;
					numNaufragos=0; //actulizamos 
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.semaforo.release();
		return numeroRescatados;
	}
	
	
	public int getRescatadosNum() {
		return rescatadosNum;
	}

}
