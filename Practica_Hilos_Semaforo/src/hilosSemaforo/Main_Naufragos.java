package hilosSemaforo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main_Naufragos {

	public static void main(String[] args) {
		
		Reloj_Naufrago reloj=new Reloj_Naufrago();
		reloj.start();

		for(int i=0;i<15;i++) {
			try {
				Barca barca=new Barca();
				ExecutorService executor=Executors.newFixedThreadPool(2);
				executor.submit(barca);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

}
