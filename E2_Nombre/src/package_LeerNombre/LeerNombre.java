package package_LeerNombre;

import java.util.Scanner;

public class LeerNombre {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		String nombre=teclado.nextLine();
        
		
		
		int numeroVocales=0;
		if(nombre.trim().length()<3) {
			//si es menor que 3 caracteres = ERROR
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
