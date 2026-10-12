package ProcesosCompetitivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;

public class Lector {

	private static final String LEER_FICHERO = "datos.txt";
	private static final String FICHERO_LOCK = "datos.lock";
	private static final Random RANDOM = new Random();

	public static void main(String[] args) {

		/*
		 * EL ESCRITOR YA FUNCIONa TENGO QUE VER QUE HACE CADA COSA DEL ESCRITOR SEGUN
		 * CLAUDE TENGGO QUE HACER LO MISMO EN LECTOR QUE HICE EN ESCRITOR PERO PARA
		 * LEER
		 */

		// tiempo entre interacciones
		long tiempoInteraciones = RANDOM.nextLong(100, 1000);

		File ficheroBloq = new File(FICHERO_LOCK);
		File ficheroLeer = new File(LEER_FICHERO);
		System.out.println("Esperando que se libere el bloqueo...");
		try (FileOutputStream lockFos = new FileOutputStream(ficheroBloq);
				FileChannel canalBloq = lockFos.getChannel();
				FileLock lock = canalBloq.lock();

				FileReader leer = new FileReader(LEER_FICHERO);
				BufferedReader lector = new BufferedReader(leer)) {
			String linea = lector.readLine();
			while (linea!= null) {
				System.out.println("Leido mensaje: "+linea);
				linea=lector.readLine();
			}

		} catch (Exception e) {

		}

	}

}
