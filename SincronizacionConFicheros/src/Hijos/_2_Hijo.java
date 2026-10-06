package Hijos;

import java.util.Scanner;

public class _2_Hijo {

	public static void main(String[] args) {
		
		
		//FALTA REVISAR ESTE CODIGO DEL HIJO
		Scanner teclado = new Scanner(System.in);
		String entradaDatos = "";
		int totalNumeros = 0;
		do {
			try {
				entradaDatos = teclado.nextLine();
				if (!entradaDatos.equals("*")) {
					totalNumeros = totalNumeros + Integer.parseInt(entradaDatos);
				}
			} catch ( NumberFormatException  e) {
				//NumberFormatException es la excepción que lanza Java cuando intentas 
				//convertir un texto a número y el texto no tiene formato de número.
				System.err.println(e.getMessage());
				// LETRA (cualquier cosa que no sea número ni *)
				System.exit(-1);
			}
		} while (!entradaDatos.equals("*"));
		System.out.println(totalNumeros);
		System.exit(0);
	}

	}


