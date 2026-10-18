package Ejercicio3_Relevos;

import java.util.Random;

public class Corredor extends Thread{
	private static final Random RANDOM = new Random();
	
	private String pais;
	private String nombre;
	
	public Corredor(String pais,String nombre) {
		this.pais=pais;
		this.nombre=nombre;
	}
	
	public void correr() {
		try {
			System.out.println(this.pais + ":" + this.nombre + " comienza su relevo");

			long total = 0;
			for (int tramo = 0; tramo < 10; tramo++) {
				//en milisegundos
				long tiempoTramo = RANDOM.nextLong(950, 1051);
				Thread.sleep(tiempoTramo);
				total = total + tiempoTramo;
			}

			System.out.printf("%s:%s ha acabado su relevo - Ha tardado: %.2f segundos%n",
					this.pais, this.nombre, total / 1000.0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		this.correr();
	}
}
