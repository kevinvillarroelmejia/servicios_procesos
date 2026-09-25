package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PedirWeb {

	
	public static void main(String[] args) throws IOException {

		
		Scanner teclado=new Scanner(System.in);
		System.out.println("WEB: ");
		String url=teclado.nextLine();
		//que sentido tiene poner la url como segundo parametro
		ProcessBuilder pb=new ProcessBuilder("google-chrome",url);
		
		File directorio=new File("/home/alumno/Escritorio");
		pb.directory(directorio);
		
		
		Process proceso=pb.start();
		
		
	}
}
