package Hijos;

import java.util.Scanner;

public class _1_Hijo {

	public static void main(String[] args) {

		//comprueba si esta vacio Y si no envio NADA
		Scanner entrada=new Scanner(System.in);
		String dato="";
		//PREGUNTA SI EXISTE ALGO EN LA ENTRADA
		if(entrada.hasNextLine()) {
		    dato = entrada.nextLine();
		}
		//si esta vacio
		if(dato.isEmpty()) {
			System.exit(-1);
		}else {
			try {
			    int numeroPasado = Integer.parseInt(dato);
			    if(numeroPasado>=1) {
			    	System.exit(-3);
			    }else if(numeroPasado<0) {
			    	System.exit(0);
			    }else if(numeroPasado==0) {
			    	System.exit(-4);
			    }
			} catch (NumberFormatException e) {
				//NO ES ENTERO
			    System.exit(-2);
			}
		}
	}
}
