package package_LlamarLeerNombre;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class LlamarLeerNombre {

	public static void main(String[] args) {
		
	
		//FICHERO PADRE
		
		// 0.- Ruta donde está compilado el hijo y clase a ejecutar
		/* Llamamos al otro programa pasando una ruta realtiva */	
		File directorio=new File("/bin");
		ProcessBuilder pb=new ProcessBuilder("java","package_LlamarLeerNombre.LlamarLeerNombre");
	   //se establece el directorio donde se encuentra el ejecutable
		pb.directory(directorio);
		
		
		// 1.- Pedimos el nombre por consola
		System.out.println("Escribe un nombre:");
		Scanner teclado = new Scanner(System.in);
		String nombre = teclado.nextLine();
		

		try {
			Process p = pb.start();

			// 2.- Se lo mandamos al hijo (con salto de línea y flush)
			OutputStream os = p.getOutputStream();
			os.write((nombre + "\n").getBytes());
			os.flush();

			// 3.- Esperamos a que termine y pintamos el valor de salida
			int exitVal = p.waitFor();
			System.out.println("Valor de salida: " + exitVal);

			// 4.- Si ha ido bien, recogemos y pintamos el número de vocales
			if (exitVal == 0) {
				Scanner salida = new Scanner(p.getInputStream());
				if (salida.hasNextLine()) {
					System.out.println("Número de vocales: " + salida.nextLine());
				}
				salida.close();
			} else {
				System.out.println("Error: el nombre tiene menos de 3 caracteres o el proceso ha fallado");
				Scanner error = new Scanner(p.getErrorStream());
				while (error.hasNextLine()) {
					System.out.println(error.nextLine());
				}
				error.close();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
