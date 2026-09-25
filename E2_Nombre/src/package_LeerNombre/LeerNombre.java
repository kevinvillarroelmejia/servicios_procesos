package package_LeerNombre;

import java.io.File;
import java.util.Scanner;

public class LeerNombre {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		String nombre=teclado.nextLine();
        /* Llamamos al otro programa pasando una ruta realtiva */	
		
		File directorio=new File("/bin");
		ProcessBuilder pb=new ProcessBuilder("java","package_LlamarLeerNombre.LlamarLeerNombre");
	    //se establece el directorio donde se encuentra el ejecutable
		pb.directory(directorio);
		int numeroVocales=0;
		if(nombre.length()<3) {
			System.exit(-1);
		}else {
			for(int i=0;i<nombre.length();i++) {
				char c=nombre.charAt(i);
				if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {
					numeroVocales++;
				}
			}
			System.out.println(numeroVocales);
			System.exit(0);
		}
	}

}
