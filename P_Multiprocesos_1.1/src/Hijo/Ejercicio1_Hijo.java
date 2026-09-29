package Hijo;

import java.util.Scanner;

public class Ejercicio1_Hijo {

	public static void main(String[] args) {

		//PROGRAMA HIJO
		/*
		 * RECIBE UN ARGUMENTO Y DEVUELVO System.exit() con los
		 * siguientes valores
		 * 
		 * 	- Devolverá -1 si el argumento viene vacío
			- Devolverá -2 si el argumento no es un entero
			- Devolverá -3 si el argumento es un entero positivo
			- Devolverá 0 si el argumento es un entero negativo
			
			- NO COMTEMPLAMOS EL 0 = SI ES 0  -4
		 */
		
		
		//comprueba si esta vacio Y si no envio NADA
		if(args.length ==0 || args[0].isEmpty()) {
			System.exit(-1);
		}else {
			try {
			    int numeroPasado = Integer.parseInt(args[0]);
			    if(numeroPasado>=1) {
			    	System.exit(-3);
			    }else if(numeroPasado<0) {
			    	System.exit(0);
			    }else if(numeroPasado==0) {
			    	System.exit(-4);
			    }
			} catch (NumberFormatException e) {
			    System.exit(-2);
			}
		}
	}
}
