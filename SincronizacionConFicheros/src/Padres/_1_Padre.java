package Padres;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class _1_Padre {

	public static void main(String[] args) throws IOException, InterruptedException {

		// PEDIMOS EL NUMERO AL USUARIO
		Scanner teclado = new Scanner(System.in);
		System.out.println("==INTRODUCE UN NUMERO==");
		String numero = teclado.nextLine();
		File directorio = new File("bin");

		
		
		// java indica que vamos a ejecutar
		// Hijo.Ejercicio1_Hijo.java ruta de la clase QUE QUEREMOS EJECUTAR (hijo)
		// Es lo que le vamos a pasar a numero
		ProcessBuilder pb = new ProcessBuilder("java", "Hijos._1_Hijo");
		pb.directory(directorio);

		/*Abrimos el fichero y escrbimos el dato de num*/
		File fEntrada=new File("dato.txt");
		FileWriter fw=new FileWriter(fEntrada);
		fw.write(numero);
		fw.flush();
		fw.close();
		
		//CREANDO LOS FICHEROS PARA RECOGER LA ENTRADA, LA SALIDA Y LOS ERRORES
		File fBat = new File("dato.txt");
		
	    pb.redirectInput(fBat);
	    
		// Ejucutamos el proceso hijo
		Process procesoHijo = pb.start();
		
		int exitVal=procesoHijo.waitFor();
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
