package Padre;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Ejercicio_3_Padre {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Scanner teclado=new Scanner(System.in);
		
		System.out.println("TU CADENA ES PALINFROMO: ");
		String cadena=teclado.nextLine();
		
		File directorio=new File("bin");
		ProcessBuilder pb=new ProcessBuilder("java","Hijo.Ejercicio_3_Hijo");
		pb.directory(directorio);
		
		Process p=pb.start();
		OutputStream os=p.getOutputStream();
		os.write((cadena+ "\n").getBytes());
		os.flush();
		
		//esperando entrega del hijo
		int exitVal=p.waitFor();
		System.out.println("Valor de la salida "+exitVal);

		Scanner respuestaHijo=new Scanner(p.getInputStream());
		System.out.println(respuestaHijo.nextLine());
	}

}
