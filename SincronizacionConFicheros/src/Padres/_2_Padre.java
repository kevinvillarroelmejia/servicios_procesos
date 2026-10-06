package Padres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class _2_Padre {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		// Ejercicio 2
		// Partiendo del ejercicio 2 de la práctica anterior, realiza los cambios
		// necesarios para que la entrada al primer programa se haga a partir de un
		// fichero llamado datos.txt
		// en lugar de pedir por consola y las salidas se redirijan a ficheros llamados
		// suma.txt y
		// error.txt.

		ArrayList<String> entradaAceptadas = new ArrayList<String>();

		try {
			FileReader fichero = new FileReader("datos_2.txt"); // ES EL PROPIO FICHERO
			BufferedReader lector = new BufferedReader(fichero); // LO TENEMOS QUE CERRAR
			String linea;
			do {
				linea = lector.readLine(); // lee una linea completa, devuelve null al final
				if (linea != null) {
					entradaAceptadas.add(linea);
				}
			} while (linea != "*"); // cuando lee null hemos terminado el fichero
			lector.close(); // cerramos y liberamos los recursos
		} catch (Exception e) {
			System.out.println("Error con el fichero");
			System.out.println(e.getMessage());
		}
		File directorio = new File("bin");
		ProcessBuilder pb = new ProcessBuilder("java", "Hijos._2_Hijo");
		pb.directory(directorio);

		try {
			// comprobacion si el ArraList esta vacio
			if (entradaAceptadas.size() > 1) {
				// SI ENTRA AQUI = el arrayList no esta vacio
				Process p = pb.start();
				// 2.- Se lo mandamos al hijo (con salto de línea y flush)
				OutputStream os = p.getOutputStream();

				for (String e : entradaAceptadas) {
					os.write((e + "\n").getBytes());
					os.flush();
				}
				// ESPERANDO LA ENTRADA DEL HIJO
				int exitVal = p.waitFor();
//				System.out.println("exitVal= " + exitVal);

				if (exitVal == 0) {
					Scanner leerHijo = new Scanner(p.getInputStream());
					// Leemos la respuesta del hijo con un nuevo Scanner
					// ESCRIBIMOS LA SUMA
					int aprobado = leerHijo.nextInt();
					File fEntrada = new File("suma_2.txt");
					FileWriter fw = new FileWriter(fEntrada);
					fw.write(aprobado);
				} else if (exitVal == -1) {
					// SI ENTRA EN EL CATCH DEL HIJO -- ES DECIR DA ERROR
					// ESCRIBIBE EN EL FICHERO ERROR
					BufferedWriter ficheroError = new BufferedWriter(new FileWriter("error.txt"));
					ficheroError.write("ERROR RECIBIDO " + (byte) exitVal);
					ficheroError.close();
					
					System.out.println("ERROR RECIBIDO DEL HIJO");
					
//					===   ME QUEDA ESTO- REVISAR CODIGO

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
