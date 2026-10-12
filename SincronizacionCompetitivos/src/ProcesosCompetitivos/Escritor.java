package ProcesosCompetitivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;

//Dos procesos acceden al mismo fichero
/*Utilizar FileLock para evitar que dos procesos accedan a la ves al mismo recurso
 * 
 * Accederan a datos,txt y a un fichero de bloqueo llamado datos.lock
 * 
 * 
 * ESCRITOR:
 * Mientras mantenga el bloqueo, escribir 10 mensajes en datos.txt.
 * Esperar un tiempo aleatorio entre 1 y 1,5 segundos.
 * Cuando termine liberar el bloqueo y mostrar que fue liberado.
 *
 * */
public class Escritor {

	private static final String FICHERO_ESCRITO = "datos.txt";
	private static final String FICHERO_LOCK = "datos.lock";
	private static final Random RANDOM = new Random();

	public static void main(String[] args) {

		// int numLineasEscritas=RANDOM.nextInt(5)+5;

		// tiempo entre iteraciones
		long tiempoEntreInteraciones = RANDOM.nextLong(100, 1000); // entre 0.1 y 1 segundo

		// APUNTANDO al fichero de bloqueo
		File ficheroBloq = new File(FICHERO_LOCK);
		// APUNTANDO al fichero donde se va escribir
		File fileEscrito = new File(FICHERO_ESCRITO);

		/*
		 * UTILIZAMOS TRY WITH RESOURCES PARA QUE LO QUE ESTAN DENTRO DE LOS PARENTESIS
		 * SE CIERREN SOLOS
		 * 
		 * TODO CADA LINEA DEPENDE DE LA ANTERIOR
		 * 
		 * 
		 * lockFos --> abre/crea datos.lock canalBloq --> consigue el canal a datos.lock
		 * lock --> bloquea ese canal / si ya esta siendo utilizado espera su turno
		 * hasta que lo suelte
		 * 
		 * fos --> abre datos.txt para escribir bytes escritor --> usa fos para escribir
		 * texto
		 * 
		 * 
		 */
		try (FileOutputStream lockFos = new FileOutputStream(ficheroBloq);
				FileChannel canalBloq = lockFos.getChannel();
				FileLock lock = canalBloq.lock();
				FileOutputStream fos = new FileOutputStream(fileEscrito);
				PrintWriter escritor = new PrintWriter(fos);) {

			System.out.println("Establecido bloqueo para escribir los mensajes...");

			for (int i = 0; i < 10; i++) {
				escritor.println("Escritor: Escrito mensaje de iteracion " + i); // escribe en el fichero
				escritor.flush(); // lo manda a disco
				Thread.sleep(tiempoEntreInteraciones);
			}
			System.out.println("Mensajes escritos");
		} catch (Exception e) {
			System.out.println("ERROR ESCRITOR " + e.getMessage());
		}
		System.out.println("Fichero liberado...");

	}

}
