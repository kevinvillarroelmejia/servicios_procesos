package Padre;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1_Padre {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		//PEDIMOS EL NUMERO AL USUARIO
		Scanner teclado= new Scanner(System.in);
		System.out.println("==INTRODUCE UN NUMERO==");
		String numero =teclado.nextLine();
		
		
		//creo que aqui va el scanner 
		//se lo pasamos como tercer parametro al pb
		
		File directorio=new File("bin");
		//java indica que vamos a ejecutar
		//Hijo.Ejercicio1_Hijo.java ruta de la clase QUE QUEREMOS EJECUTAR (hijo)
		//Es lo que le vamos a pasar a numero
		ProcessBuilder pb =new ProcessBuilder("java","Hijo.Ejercicio1_Hijo.java",numero);
		
		//ESTO NO HACE FALTA?
		//		pb.directory(directorio);
		
		//Ejucutamos el proceso hijo
		Process procesoHijo=pb.start();
		
		//Como ya le hemos pasado el numero con el ProcessBuilder como String
		//esperamos a que nos devuelva la respuesta
		int exitVal=procesoHijo.waitFor();//recoge el codigo que el hijo ha enviado MEDIANTE System.exit()
		
		//MOSTRAMOS EL CODIGO RECIBIDO POR EL HIJO
		System.out.println("Valor de salida: "+exitVal);
		
		//FALTA COMPROBAR QUE SIGNIFICADO TIENE EL CODIGO DEL HIJO
		
		
		
	}
}
