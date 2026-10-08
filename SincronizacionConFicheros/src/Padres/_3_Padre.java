package Padres;

import java.io.File;

public class _3_Padre {

	public static void main(String[] args) {

//		Ejercicio 3
//		Partiendo del ejercicio 3 de la práctica anterior, realiza los cambios necesarios para que la
//		entrada al primer programa se haga a partir de un fichero llamado texto.txt en lugar de pedir
//		por consola y las salidas se redirijan a ficheros llamados palindromo.txt y error.txt.

		File directorio=new File("bin");
		ProcessBuilder pb=new ProcessBuilder("java","Hijos._3_Hijo");
		
		pb.directory(directorio);
		
		try {
			pb.redirectInput(new File("datos3.txt"));
			pb.redirectOutput(new File("palindromo.txt"));
			pb.redirectError(new File("error.txt"));
			
			//ARRANCAMOS EL HIJO 
			Process pHijo=pb.start();
		}catch (Exception e) {
			System.out.println("error main");
		}
	}

}
