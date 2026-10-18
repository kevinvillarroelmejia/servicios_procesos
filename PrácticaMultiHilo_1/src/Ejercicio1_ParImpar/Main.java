package Ejercicio1_ParImpar;

public class Main {

	public static void main(String[] args) {
		
		/* Decicion de hacerlo con Threands o Runneable
		 * Ejercicio 1 - Threands 
		 * Ejercicio 2 - Threands
		 * Ejercicio 3 - Threands
		 * Ejercicio 4 - Runneable
		 * Ejercicio 5 - Runneable
 		 * */
		
		Numero hilo1=new Numero(true);
		Numero hilo2=new Numero(false);
		
		hilo1.start();
		hilo2.start();
		
		
		
	}

}
