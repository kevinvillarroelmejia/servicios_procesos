package Padre;

import java.io.File;

public class Ejercicio1_Padre {

	public static void main(String[] args) {
		
		//creo que aqui va el scanner 
		//se lo pasamos como tercer parametro al pb
		
		File directorio=new File("bin");
		ProcessBuilder pb =new ProcessBuilder("java","Hijo.Ejercicio1_Hijo.java");
		pb.directory(directorio);
		
		
		
		
		
	}
}
