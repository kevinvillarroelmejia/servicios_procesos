package Hijos;

import java.util.Scanner;

public class _3_Hijo {

	public static void main(String[] args) {

		//este recibe uina cadena y comprueba si es palindromo o no
		
		Scanner teclado=new Scanner(System.in);
		String palabraPalindromo=teclado.nextLine();

		String alReves="";
		for(int i=palabraPalindromo.length()-1;i>=0;i--) {
			alReves=alReves+palabraPalindromo.charAt(i);
		}
		if(alReves.equalsIgnoreCase(palabraPalindromo)) {
			System.out.println("Es palindromo ");
		}else {
			System.out.println("No es palindromo");
		}
		
	}

}
