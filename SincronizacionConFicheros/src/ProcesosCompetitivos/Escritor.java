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

		//numero de filas que se deben escribir
//		int numLineasEscritas=RANDOM.nextInt(5)+5;
		
		//tiempo entre iteraciones
		long tiempoEntreInteraciones=RANDOM.nextLong(100,1000); //entre 0.1 y 1 segundo

		//marca de bloqueo
		File ficheroBloq=new File (FICHERO_LOCK);		
		try(FileOutputStream lockFos=new FileOutputStream(ficheroBloq)) {
			//FICHERO A ESCRIBIR
			File fileEscrito=new File(FICHERO_ESCRITO);
			FileChannel canalBloq=lockFos.getChannel();
			FileLock lock=canalBloq.lock();
			FileOutputStream fos = new FileOutputStream(fileEscrito, true);
			PrintWriter escritor=new PrintWriter(fileEscrito);
			System.out.println("Establecido bloqueo para escribir los mensajes...");
			for(int i=0;i<10;i++) {
				Thread.sleep(tiempoEntreInteraciones);
				System.out.println("Lector: Escrito mensaje de iteración "+i);
				escritor.println("Lector: Escrito mensaje de iteracion "+i);
				escritor.flush();
			}
			escritor.close();
		} catch (Exception e) {
			System.out.println("ERROR ESCRITOR");
		}
	}

}
