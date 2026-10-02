package Padre;

import java.io.File;
import java.io.IOException;

public class PedirFichero_PADRE {

	public static void main(String[] args) throws IOException {
		//creamos objeto File al directorio donde esta Ejemplo2
        /* Llamamos al otro programa pasando una ruta absoluta */
		//File directorio = new File("/home/julio/Docencia/workspaces/PSP_CURSO/UT1/UT1_5_CalcularParImpar/bin");	

        /* Llamamos al otro programa pasando una ruta realtiva */	
        File directorio = new File("bin");	

		//El proceso a ejecutar es Ejemplo2			
		ProcessBuilder pb = new ProcessBuilder("java","Hijo.CalcularNumero");		
	    //se establece el directorio donde se encuentra el ejecutable
	    pb.directory(directorio);
	    
	    File fBat = new File("entrada.txt");
	    File fOut = new File("salida.txt");
	    File fErr = new File("error.txt");
	 
	    pb.redirectInput(fBat);
	    pb.redirectOutput(fOut);
	    pb.redirectError(fErr); 
		
	    //se ejecuta el proceso
		Process p = pb.start();

	}

}