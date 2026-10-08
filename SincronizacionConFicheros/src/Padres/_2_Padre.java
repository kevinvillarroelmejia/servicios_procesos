package Padres;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class _2_Padre {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		// Ejercicio 2
		// Partiendo del ejercicio 2 de la práctica anterior, realiza los cambios
		// necesarios para que la entrada al primer programa se haga a partir de un
		// fichero llamado datos.txt
		// en lugar de pedir por consola y las salidas se redirijan a ficheros llamados
		// suma.txt y
		// error.txt.

		File directorio = new File("bin");
		ProcessBuilder pb = new ProcessBuilder("java", "Hijos._2_Hijo");
		pb.directory(directorio);

		try {
			//ORDEN: PARA HACER SINCRONIZACION
			//DIRECTORY
			//REDIRECTORY
			//START()
			//WAITFOR()
			
			//REDIRIGIMOS LAS SALIDAS DEL HIJO
			pb.redirectInput(new File("datos_2.txt"));
			pb.redirectOutput(new File("suma_2.txt"));
			pb.redirectError(new File("error.txt"));

			//INICIAMOS CON LAS CONFIGURACIONES DE LOS redirect
			Process p = pb.start();

			// ESPERAMOS SALIDA HIJO QUE ENTRA AL PADRE
//			int exitVal = p.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
