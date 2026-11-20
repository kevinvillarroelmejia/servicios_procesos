package hilosSemaforo;

import java.util.Random;

public class Barca implements Runnable {

	private static final Random RANDOM = new Random();

	public static int contador = 0;
	private int idBarca = 0;
	// ponerle prioridad a una barca

	private int rescatadosPorHilo = 0;

	private Isla isla;

	public Barca(Isla isla) {
		this.idBarca = contador;
		contador++;
		this.isla = isla;
	}

	@Override
	public void run() {
		System.out.println("La balsa actual es la Balsa " + this.idBarca);
		int numRescatados = -1;
		do {

				numRescatados = isla.rescatarNaufragos();

				System.out.println("Quedan " + isla.getNumNaufragos() + "");

				try {
					if (numRescatados == 15) {
						rescatadosPorHilo = rescatadosPorHilo + 15;
						System.out.println("La balsa actual es " + idBarca + " y rescatamos a 15");
					} else if (numRescatados < 15) {
						System.out.println(
								"La balsa actual es " + idBarca + " y rescatamos a " + isla.getRescatadosNum());
						rescatadosPorHilo = rescatadosPorHilo + numRescatados;
					}

					// Tiempo random de vuelta a la barca entre 1,8s y 2s
					int vueltaAlBarco = (int) RANDOM.nextLong(1800, 2001);
					Thread.sleep(vueltaAlBarco);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		} while (numRescatados != 0);
		if (numRescatados == 0) {
			System.out.println("Ya no quedan naufragos y la balsa " + this.getIdBarca()
					+ " esta volviendo a su puesto. Ha rescatado a " + this.rescatadosPorHilo + "");
		}
	}

	public int getIdBarca() {
		return idBarca;
	}

}
