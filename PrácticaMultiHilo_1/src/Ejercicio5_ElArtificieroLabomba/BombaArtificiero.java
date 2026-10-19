package Ejercicio5_ElArtificieroLabomba;

import java.util.Random;

public class BombaArtificiero {
	
	private static final Random RANDOM = new Random();
	
	public void cuentaAtrasBomba() {

	}

	public void explotarBomba() {
		for (int i = 9; i <= 0; i--) {
			System.out.println("La bomba explota en  " + i + " segundos\n");
		}

	}

	
	//metodo sin acabar
	public void desactivarBomba() {
		long tiempoTramo = RANDOM.nextLong(9500, 10501);
		
	}
}
