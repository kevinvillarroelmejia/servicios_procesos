package hilosSemaforo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main_Naufragos {

	public static void main(String[] args) {

		Reloj_Naufrago reloj = new Reloj_Naufrago();
		
		Semaphore semaforo=new Semaphore(2);
		
		Isla isla=new Isla(semaforo);
		
		Barca b1Hilo = new Barca(isla);
		
		
		Thread hiloPrioridad=new Thread(b1Hilo);
		hiloPrioridad.setPriority(10);
		
		Barca b2Hilo = new Barca(isla);
		Barca b3Hilo = new Barca(isla);
		Barca b4Hilo = new Barca(isla);
		Barca b5Hilo = new Barca(isla);
		try {
			ExecutorService executor = Executors.newFixedThreadPool(6);
			executor.submit(b1Hilo);
			executor.submit(b2Hilo);
			executor.submit(b3Hilo);
			executor.submit(b4Hilo);
			executor.submit(b5Hilo);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
