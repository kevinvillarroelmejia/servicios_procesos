package Ejercicio3_Relevos;

import java.util.Random;

public class Corredor extends Thread {
	private static final Random RANDOM = new Random();

	private String pais;
	private String nombre;

	public Corredor(String pais, String nombre) {
		this.pais = pais;
		this.nombre = nombre;
	}

	public void correr() {
		try {
			System.out.println(this.pais + ":" + this.nombre + " comienza su relevo");

			// en milisegundos
			long tiempoTramo = RANDOM.nextLong(950, 1051);
			long total = tiempoTramo * 10;
			Thread.sleep(tiempoTramo);
			total = total + tiempoTramo;

			System.out.println(this.nombre + " ha acabado - Ha tardado: " + total / 1000.0);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		this.correr();
	}
}
