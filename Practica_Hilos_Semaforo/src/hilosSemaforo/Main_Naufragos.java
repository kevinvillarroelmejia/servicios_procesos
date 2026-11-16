package hilosSemaforo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main_Naufragos {

	public static void main(String[] args) {

		Reloj_Naufrago reloj = new Reloj_Naufrago();
		reloj.start();

		Barca b1Hilo = new Barca();
		Barca b2Hilo = new Barca();
		Barca b3Hilo = new Barca();
		Barca b4Hilo = new Barca();
		Barca b5Hilo = new Barca();

		try {
			ExecutorService executor = Executors.newFixedThreadPool(5);
			executor.submit(b1Hilo);
			executor.submit(b2Hilo);
			executor.submit(b3Hilo);
			executor.submit(b4Hilo);
			executor.submit(b5Hilo);

			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
