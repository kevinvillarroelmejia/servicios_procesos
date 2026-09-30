package Padre;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1_Padre {

	public static void main(String[] args) throws IOException, InterruptedException {

		// PEDIMOS EL NUMERO AL USUARIO
		Scanner teclado = new Scanner(System.in);
		System.out.println("==INTRODUCE UN NUMERO==");
		String numero = teclado.nextLine();


		File directorio = new File("bin");

		// java indica que vamos a ejecutar
		// Hijo.Ejercicio1_Hijo.java ruta de la clase QUE QUEREMOS EJECUTAR (hijo)
		// Es lo que le vamos a pasar a numero
		ProcessBuilder pb = new ProcessBuilder("java", "Hijo.Ejercicio1_Hijo", numero);

		pb.directory(directorio);

		// Ejucutamos el proceso hijo
		Process procesoHijo = pb.start();

		// Como ya le hemos pasado el numero con el ProcessBuilder como String
		// esperamos a que nos devuelva la respuesta
		int exitVal = procesoHijo.waitFor();// recoge el codigo que el hijo ha enviado MEDIANTE System.exit()
		
		//Las siguientes Lineas son para que salga el valor 
		//independientemente del sistemas (Windows o linux)
		if (exitVal > 127) {
		    exitVal = exitVal - 256;
		}
		// MOSTRAMOS EL CODIGO RECIBIDO POR EL HIJO
		System.out.println("Valor de salida: " + exitVal);

		// FALTA COMPROBAR QUE SIGNIFICADO TIENE EL CODIGO DEL HIJO
		if (exitVal == -1) {
			System.out.println("El argumento viene vacio");
		} else if (exitVal == -2) {
			System.out.println("El argumento no es un NUMERO");
		} else if (exitVal == -3) {
			System.out.println("El argumento es un entero postivo");
		} else if (exitVal == 0) {
			System.out.println("El argumento es un entero negativo");
		}

	}
}
