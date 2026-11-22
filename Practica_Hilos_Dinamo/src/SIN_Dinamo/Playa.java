package SIN_Dinamo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Playa { // ESTA ES LA CLASE COMUN
	// las clases comunes tiene el metodo o parte de codigo syncronized
	// y el semaforo

	private static final Random RANDOM = new Random();

	private int numSoldados = 400000; // soltados a rescatar
	private Semaphore semaforo;

	public Playa(Semaphore semaforo) {// recibe el semaforo
		this.semaforo = semaforo;
	}

	public int rescatarNaufragos(Object objeto) {
		int azarRescatados=0;
		try {
			synchronized (this) {
				// SI el objeto es barca entran entre 1500 y 2000 soltados
				if (objeto instanceof Barca) {
					azarRescatados = (int) RANDOM.nextLong(1500, 2000);
					if(azarRescatados>numSoldados) {
						azarRescatados=numSoldados;
						numSoldados=0;
					}else {
						numSoldados = numSoldados - azarRescatados;
					}

					// si el objeto es de lancha entran entre 500 y 100 soldados - AZAR
				} else if (objeto instanceof Lancha) {
					azarRescatados = (int) RANDOM.nextLong(500, 1000);
					if(azarRescatados>numSoldados) {
						azarRescatados=numSoldados;
						numSoldados=0;
					}else {
						numSoldados = numSoldados - azarRescatados;
					}
				}
			}
			Thread.sleep(1000);//tiempo de embarcar y desmbarcar sumados
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return azarRescatados;
	}

}
