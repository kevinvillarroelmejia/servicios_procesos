package Ejercicio5_ElArtificieroLabomba;

import java.util.Random;

public class Tarea implements Runnable{
	
	private static final Random RANDOM = new Random();
	private Thread hiloArtificiero;

	
	public void cuentaAtrasBomba() {
		for (int i = 9; i >= 0; i--) {
			System.out.println("La bomba explota en  " + i + " segundos\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}
		hiloArtificiero.interrupt();
		}
	
	
	//metodo sin acabar
	public void desactivarBomba() {
		boolean desactivar=false;
		try {
			int tiempoTramo = (int) RANDOM.nextLong(9500, 10501);
			Tarea bomba=new Tarea();
			Thread.sleep(tiempoTramo);
			desactivar=true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("El artificiero a muerto");
		}
	}

	@Override
	public void run() {
		boolean bandera=false;
		
			
		
	}
}
