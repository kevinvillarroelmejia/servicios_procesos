package hilosSemaforo;

import java.util.Random;

public class Barca implements Runnable {

	private static final Random RANDOM = new Random();

	private int idBarca;
	// ponerle prioridad a una barca

	private Isla isla;

	public Barca() {
		idBarca++;
	}

	@Override
	public void run() {
		System.out.println("La balsa actual es la Balsa " + this.idBarca);
		int numRescatados = isla.rescatarNaufragos();
		while (numRescatados != 0) {
			try {
				
				if (numRescatados == 15) {
					System.out.println("La balsa actual es " + idBarca + " y rescatamos a 15");
				} else if (numRescatados < 15) {
					System.out.println("La balsa actual es " + idBarca + " y rescatamos a " + isla.getRescatadosNum());
				} else if (numRescatados == 0) {
					System.out.println("La balsa actual es " + idBarca + " y rescatamos a 0");
				}
				
				// Tiempo random de vuelta a la barca entre 1,8s y 2s
				int vueltaAlBarco = (int) RANDOM.nextLong(1800, 2001);
				Thread.sleep(vueltaAlBarco);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getIdBarca() {
		return idBarca;
	}

}
