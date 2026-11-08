package proyecto_Nominas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/*
 * SubApGenFT ---> generación del Fichero de Transferencias
 * envia el fichero de la nominas
 *
 * Entrada (por su entrada estándar, se la manda la ApPrinc, una línea por dato)
 *  
 * Salida: crea el fichero de transferencias con el formato de lineas (cuenta;importe)
 *   Mientras lo crea existe el fichero de bloqueo "transferencias.bloqueo".
 *
 * Codigos de salida (Syso)
 *   0 --> fichero generado correctamente
 *   1 --> Eerror simulado al generar el fichero (aleatorio <= 30)
 *   2 -> error de entrada/salida al crear el fichero
 *   3 --> el fichero de transferencias ya existía (no se genera)
 *   4 -> datos de entrada incorrectos
 */
public class GenradorFcihero {

	//fiichero de bloqueo
	private static final String FICHERO_BLOQUEO = "transferencias.bloqueo";

	// codigos de salida para identificarlos mejor
	private static final int SALIDA_OK = 0;
	private static final int SALIDA_ERROR_ALEATORIO = 1;
	private static final int SALIDA_ERROR_ES = 2;
	private static final int SALIDA_YA_EXISTE = 3;
	private static final int SALIDA_DATOS_INCORRECTOS = 4;

	private static final Random RANDOM = new Random();

	/*
	 * Programa principal: simula el posible error, lee los datos y genera el fichero.
	 * Parametros: los datos llegan por la entrada estándar.
	 * Devuelve: el código de salida con System.exit 0,1,2,3 o 4.
	 */
	public static void main(String[] args) throws InterruptedException {

		// esperamos entre 1 y 5 segundos
		int espera = RANDOM.nextInt(1000, 5001);
		Thread.sleep(espera);

		// Simulamos que el fichero se genera con errores (30% de las veces)
		int aleatorio = RANDOM.nextInt(1, 101); // entre 1 y 100
		if (aleatorio <= 30) {
			System.out.println("Error simulado al generar el fichero (aleatorio = " + aleatorio + ")");
			System.exit(SALIDA_ERROR_ALEATORIO); //==1
		}

		//luego leemos los datos que nos manda la ApPrinc
		Scanner sc = new Scanner(System.in, "UTF-8"); // UTF-8 == castellano
		String ruta;
		String nombre;
		int numTransferencias;
		try {
			ruta = sc.nextLine();
			nombre = sc.nextLine();
			numTransferencias = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("Datos de entrada incorrectos");
			System.exit(SALIDA_DATOS_INCORRECTOS);
			return;
		}

		//  Solo se genera el fichero si no existe
		File fTransferencias = new File(ruta, nombre);
		if (fTransferencias.exists()) {//existe?
			System.out.println("El fichero " + fTransferencias.getPath() + " ya existe");
			System.exit(SALIDA_YA_EXISTE); //==3
		}

		// generamos el fichero protegido con el fichero de bloqueo
		int salida = generarFichero(ruta, fTransferencias, numTransferencias);
		System.exit(salida);
	}

	/*
	 * Crea el fichero de bloqueo, escribe las transferencias y borra el bloqueo
	 * recibe: ruta de la carpeta, fichero de transferencias y numero de transferencias a escribir
	 * devuelve: SALIDA_OK si todo va bien o SALIDA_ERROR_ES si falla la E/S
	 */
	private static int generarFichero(String ruta, File fTransferencias, int numTransferencias)
			throws InterruptedException {
		int salida = SALIDA_OK;//==0
		File bloqueo = new File(ruta, FICHERO_BLOQUEO);
		try {
			// Creamos el bloqueo ANTES del fichero de transferencias:
			// así el procesador nunca ve el fichero sin el bloqueo mientras se escribe
			bloqueo.createNewFile();

			try (FileWriter fw = new FileWriter(fTransferencias)) {
				for (int i = 0; i < numTransferencias; i++) {
					fw.write(generarTransferencia() + "\n");
					fw.flush();
					Thread.sleep(10); // 10 ms entre una transferencia y la siguiente
				}
			}
			System.out.println("Fichero generado: " + fTransferencias.getPath());
		} catch (IOException e) {
			System.out.println("Error de E/S al crear el fichero: " + e.getMessage());
			salida = SALIDA_ERROR_ES;//==2
		} finally {
			//da igual lo que pasee tenemos que  borrar el bloqueo para que el procesador no espere para siempre
			bloqueo.delete();
		}
		return salida;
	}

	/*
	 * Genera una transferencia aleatoria con el formato "cuenta;importe".
	 * Devuelve: la línea de la transferencia, por ejemplo "262198823;2755.23".
	 */
	private static String generarTransferencia() {
		int primerDigito = RANDOM.nextInt(1, 3);// 1 (interna) o 2 (externa)
		int resto = RANDOM.nextInt(10000000, 100000000);//resto entre 10000000 y 99999999
		long cuenta = primerDigito * 100000000L + resto; //contatenamos

		// Importe entre 1500 y 3000 con 2 decimales
		double importe = redondear(RANDOM.nextDouble(1500, 3000));
		return cuenta + ";" + importe;
	}

	/*
	 * devuelve un numero redondeado que fue recibido
	 */
	private static double redondear(double numero) {
		return Math.round(numero * 100) / 100.0;
	}
}