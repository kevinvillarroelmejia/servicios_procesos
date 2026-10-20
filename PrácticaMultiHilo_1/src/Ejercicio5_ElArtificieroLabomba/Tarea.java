package Ejercicio5_ElArtificieroLabomba;

import java.util.Random;

public class Tarea implements Runnable {

	private static final Random RANDOM = new Random();
	private Thread hiloArtificiero;

	private boolean soyBomba;

	public Tarea(boolean soyBomba) {
		this.soyBomba = soyBomba;
	}

	public void cuentaAtrasBomba() {

		for (int i = 9; i >= 0; i--) {

			System.out.println("La bomba explota en  " + i + " segundos\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("La bomba explota");
	}
	// metodo sin acabar
	public void desactivarBomba() {
		System.out.println("El artificiero empezo a desactivar la bomba");
		try {
			int tiempoTramo = (int) RANDOM.nextLong(9500, 10501);
			Thread.sleep(tiempoTramo);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("El artificiero a muerto");
		}
		System.out.println("El artificiero desactivo la bomba");
	}

	@Override
	public void run() {
		boolean bandera = false;
		if (soyBomba) {
			cuentaAtrasBomba();
		} else {
			desactivarBomba();
		}

	}
}
