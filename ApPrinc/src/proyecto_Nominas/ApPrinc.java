package proyecto_Nominas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * Les pasa los datos por stream. Si el generador falla, destruye el procesador; si no, muestra el informe.
 *
 *  datos por teclado (nombre, ruta, nº de transferencias y nº de hilos).
 * luego muestra mensajes del procesador e informe final por pantalla.
 *
 * Códigos de salida: 0 OK · -1 falla el procesador · -2 falla el generador · -3 error de E/S
 */
public class ApPrinc {

	// vaalores por defecto si el usuario solo pulsa Enter
	private static final String NOMBRE_DEFECTO = "transfer_data";
	private static final String RUTA_DEFECTO = "./home/pruebas/";

	private static final String FICHERO_BLOQUEO = "transferencias.bloqueo";

	// Comandos para lanzar los hijos: "../PROYECTO/bin" y "paquete.Clase"
	// (igual que en MainProcessEjemplo3 del profe, con -cp)
	private static final String[] COMANDO_PROCESADOR = { "java", "-cp", "../SubApProcFT/bin", "proyecto_Nominas.SubApProcFT" };
	private static final String[] COMANDO_GENERADOR = { "java", "-cp", "../SubApGenFT/bin", "proyecto_Nominas.GenradorFcihero" };

	// Códigos de salida
	private static final int SALIDA_OK = 0;
	private static final int SALIDA_ERROR_PROCESADOR = -1;
	private static final int SALIDA_ERROR_GENERADOR = -2;
	private static final int SALIDA_ERROR_ES = -3;

	/*
	 pide datos, lanza los dos hijos en orden y controla cómo terminan
	  el código de salida con System.exit 
	 */
	public static void main(String[] args) throws InterruptedException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Dime el nombre del fichero a generar (Enter = " + NOMBRE_DEFECTO + "):");
		String nombre = sc.nextLine();
		if (nombre.isEmpty()) {
			nombre = NOMBRE_DEFECTO;
		}

		System.out.println("Dime la ruta donde lo guardo, acabada en barra (Enter = " + RUTA_DEFECTO + "):");
		String ruta = sc.nextLine();
		if (ruta.isEmpty()) {
			ruta = RUTA_DEFECTO;
		}
		if (!ruta.endsWith("/") && !ruta.endsWith("\\")) {
			ruta = ruta + File.separator; // por si el usuario no pone la barra final
		}

		int numTransferencias = pedirNumeroPositivo(sc, "Dime el número de transferencias a generar");
		int numHilos = pedirNumeroPositivo(sc, "Dime el número de hilos con el que procesar el fichero");

		// creamos la carpeta y borramos el fichero si ya existía
		File carpeta = new File(ruta);
		carpeta.mkdirs();
		File fTransferencias = new File(ruta, nombre);
		if (fTransferencias.exists()) {
			fTransferencias.delete();
			System.out.println("El fichero ya existía y se ha borrado.");
		}
		File bloqueo = new File(ruta, FICHERO_BLOQUEO);
		if (bloqueo.exists()) {//eliminamos el bloqueo por si existe
			bloqueo.delete();
		}

		try {
			//Lanzamos PRIMERO el procesador y le mandamos sus 4 datos
			Process procesador = new ProcessBuilder(COMANDO_PROCESADOR).start();
			enviarDatos(procesador, ruta + "\n" + nombre + "\n" + numTransferencias + "\n" + numHilos + "\n");

			// lanzamos DESPUÉS el generador y le mandamos sus 3 datos
			Process generador = new ProcessBuilder(COMANDO_GENERADOR).start();
			enviarDatos(generador, ruta + "\n" + nombre + "\n" + numTransferencias + "\n");

		//Esperamos al generador y comprobamos su código de salida
			int salidaGenerador = generador.waitFor();
			System.out.println("Valor de Salida del generador: " + salidaGenerador);

			if (salidaGenerador != 0) {
				// Si llega aqui el generador fallo, matamos el procesador
				System.out.println("ERROR en la generación del fichero: " + explicarSalidaGenerador(salidaGenerador));
				procesador.destroy();
				procesador.waitFor();
				System.out.println("Se ha detenido el proceso de procesamiento de transferencias.");
				System.exit(SALIDA_ERROR_GENERADOR);
			}

			//El generador fue bien: leemos todo lo que imprime el procesador
			mostrarSalida(procesador.getInputStream());

			// Comprobamos cómo acabo el procesador
			int salidaProcesador = procesador.waitFor();
			System.out.println("Valor de Salida del procesador: " + salidaProcesador);
			if (salidaProcesador != 0) {
				System.out.println("ERROR en el procesamiento del fichero de transferencias.");
				System.exit(SALIDA_ERROR_PROCESADOR);
			}

		} catch (IOException e) {
			System.out.println("Error de E/S al lanzar los procesos: " + e.getMessage());
			System.exit(SALIDA_ERROR_ES);
		}

		System.exit(SALIDA_OK);
	}

//	 Pide un número entero mayor que 0 y lo repite hasta que el usuario lo escriba bien.
	private static int pedirNumeroPositivo(Scanner sc, String mensaje) {
		int numero = 0;
		while (numero <= 0) {
			System.out.println(mensaje + ":");
			try {
				numero = Integer.parseInt(sc.nextLine());
				if (numero <= 0) {
					System.out.println("Tiene que ser un número mayor que 0.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Eso no es un número entero.");
			}
		}
		return numero;
	}

	/*
	 * Envía datos a un proceso hijo por su stream de entrada 
	 */
	private static void enviarDatos(Process proceso, String datos) throws IOException {
		OutputStream os = proceso.getOutputStream();
		OutputStreamWriter ow = new OutputStreamWriter(os, "UTF-8"); // UTF-8 para tildes y ñ
		BufferedWriter bw = new BufferedWriter(ow);
		bw.write(datos);
		bw.flush(); // sin flush los datos se quedan en el buffer y el hijo no los recibe
	}

	
//	  Lee y muestra por pantalla todo lo que escribe un proceso hijo.
	 
	private static void mostrarSalida(InputStream is) throws IOException {
		Scanner sc = new Scanner(is, "UTF-8");
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		is.close();
	}

	/*
	 * DEpende del codigo de salida del generador muestra un mensaje u otro
	 */
	private static String explicarSalidaGenerador(int codigo) {
		String mensaje;
		if (codigo == 1) {
			mensaje = "el fichero se genero con errores (error simulado).";
		} else if (codigo == 2) {
			mensaje = "error de entrada/salida al crear el fichero.";
		} else if (codigo == 3) {
			mensaje = "el fichero de transferencias ya existia.";
		} else if (codigo == 4) {
			mensaje = "los datos recibidos no son correctos.";
		} else {
			mensaje = "codigo de error desconocido.";
		}
		return mensaje;
	}
}