package Padre;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Ejercicio_2_Padre {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * ===Ejercicio 2===
		 * 
		 * Crea un programa que lee desde la entrada estándar varias líneas (un número
		 * desconocido de líneas). Por cada línea leída debe validar si es un número,
		 * una cadena o un *. Si en alguna línea hay una cadena, el programa saldrá con
		 * un error -1 (System.exit()). Si no hay cadenas, los números se sumarán hasta
		 * que aparezca un “*”. Cuando lea un *, dejará de leer la entrada estándar. El
		 * programa dejará en la salida estándar la suma de los números recibidos y
		 * devolverá que todo ha ido correctamente. Haz otro programa que le pida al
		 * usuario que vaya metiendo números por consola. El programa debe permitir
		 * meter números hasta que se introduzca un *. No debe validar si lo que se mete
		 * son números o no. Una vez se haya acabado de meter números, llamará al
		 * programa anterior pasando los datos que se han recogido de la consola y
		 * pintará la salida con la suma de los números introducidos.
		 */

		Scanner teclado = new Scanner(System.in);

		String entrada = "";
		ArrayList<String> entradaAceptadas = new ArrayList<String>();

		do {
			System.out.println("===NUMERO, LETRA, SALIR --> * === ");
			entrada = teclado.nextLine();
			if (!entrada.equals("*")) {
				entradaAceptadas.add(entrada);
			}
		} while (!entrada.equals("*"));
		entradaAceptadas.add("*");
		// CREACION DE PROCESO
		File directorio = new File("bin");
		ProcessBuilder pb = new ProcessBuilder("java", "Hijo.Ejercicio_2_Hijo");
		pb.directory(directorio);
		try {
			// comprobacion si el ArraList esta vacio
			if (entradaAceptadas.size() > 1) {
				// SI ENTRA AQUI = el arrayList no esta vacio
				Process p = pb.start();
				// 2.- Se lo mandamos al hijo (con salto de línea y flush)
				OutputStream os = p.getOutputStream();

				for (String e : entradaAceptadas) {
					os.write((e+"\n").getBytes());
					os.flush();
				}
				int exitVal=p.waitFor();
				System.out.println("exitVal= "+exitVal);
				if(exitVal==0) {
					//Leemos la respuesta del hijo con un nuevo Scanner
					Scanner leerHijo=new Scanner(p.getInputStream());
					System.out.println(leerHijo.nextLine());
				}else if(exitVal==-1){
					System.out.println("Se metio algo que no es un numero");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
