package proyecto_Nominas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


//  Procesa el fichero de transferencias con varios hilos.
//  Espera a que exista el fichero y no esté el bloqueo, lo carga y lo reparte entre los hilos.
// 
//  Códigos de salida: 0 OK · 1 datos incorrectos · 2 error de E/S
public class SubApProcFT {

	// fichero de bloqueo el mismo del generador
	private static final String FICHERO_BLOQUEO = "transferencias.bloqueo";

	// Códigos de salida
	private static final int SALIDA_OK = 0;
	private static final int SALIDA_DATOS_INCORRECTOS = 1;
	private static final int SALIDA_ERROR_ES = 2;

	private static final Random RANDOM = new Random();

//	 lee los datos, lo procesa con hilos y pinta el informe.
	public static void main(String[] args) throws InterruptedException {

		/*Leemos los datos que nos manda la ApPrinc por el stream de entrada*/
		Scanner sc = new Scanner(System.in, "UTF-8"); // UTF-8 para que lleguen bien tildes y ñ
		String ruta;
		String nombre;
		int numTransferencias;
		int numHilos;
		try {
			ruta = sc.nextLine();
			nombre = sc.nextLine();
			numTransferencias = Integer.parseInt(sc.nextLine());
			numHilos = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("Datos de entrada incorrectos");
			System.exit(SALIDA_DATOS_INCORRECTOS);
			return;
		}

		//Esperamos a que el fichero exista y ya no esté el bloqueo
		File fTransferencias = new File(ruta, nombre);
		File bloqueo = new File(ruta, FICHERO_BLOQUEO);
		esperarFichero(fTransferencias, bloqueo);

		try {
	// Cargamos el fichero en memoria
			List<Transferencia> transferencias = leerFichero(fTransferencias);
			System.out.println("Transferencias leidas del fichero: " + transferencias.size()
					+ " (esperadas: " + numTransferencias + ")");

			//Saldo inicial = aleatorio(1500, 2700) x Nº de transferencias
			double saldoInicial = redondear(RANDOM.nextDouble(1500, 2700) * numTransferencias);
			System.out.println("Importe total transferencias = " + redondear(sumarImportes(transferencias)));
			System.out.println("El saldo de la cuenta es: " + saldoInicial);

			//Objeto compartido por todos los hilos
			GestorTransferencias gestor = new GestorTransferencias(transferencias, saldoInicial, ruta);

			// Pool de hilos con ExecutorService (como en Relevos_Sync)
			ExecutorService executor = Executors.newFixedThreadPool(numHilos);
			HiloTransferencia[] hilos = new HiloTransferencia[numHilos];
			for (int i = 0; i < numHilos; i++) {
				hilos[i] = new HiloTransferencia(i, gestor);
				executor.submit(hilos[i]);
			}

			executor.shutdown();//No acepta más tareas
			executor.awaitTermination(1, TimeUnit.HOURS);//esperamos a que terminen todos los hilos

			// 7) Cerramos los ficheros cuando los hilos ya han parado
			gestor.cerrarFicheros();

			// 8) Informe final
			pintarInforme(hilos, gestor);

		} catch (IOException e) {
			System.out.println("Error de E/S: " + e.getMessage());
			System.exit(SALIDA_ERROR_ES);
		}

		System.exit(SALIDA_OK);
	}
	// sse queda esperando cada segundo, a que exista el fichero de transferencias y ya no exista el bloqueo
	private static void esperarFichero(File fTransferencias, File bloqueo) throws InterruptedException {
		while (!fTransferencias.exists() || bloqueo.exists()) {
			System.out.println("Esperando a que el fichero de transferencias este disponible...");
			Thread.sleep(1000);
		}
		System.out.println("Fichero de transferencias disponible. Empezamos a procesar.");
	}

	/*
	 * Lee el fichero de transferencias y lo guarda en una lista yla devuelve
	 */
	private static List<Transferencia> leerFichero(File fTransferencias) throws IOException {
		List<Transferencia> lista = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fTransferencias))) {
			String linea = br.readLine();
			while (linea != null) {
				String[] partes = linea.split(";"); // partes[0] = cuenta, partes[1] = importe
				lista.add(new Transferencia(partes[0], Double.parseDouble(partes[1])));
				linea = br.readLine();
			}
		}
		return lista;
	}

	/*
	 * Suma los importes de todas las transferencias.
	 * Parámetros: la lista de transferencias.
	 * Devuelve: la suma de los importes.
	 */
	private static double sumarImportes(List<Transferencia> transferencias) {
		double suma = 0;
		for (Transferencia t : transferencias) {
			suma += t.getImporte();
		}
		return suma;
	}

//	  Pinta el informe final
//	  se le pasa un array de hilos con sus cuentas
	 
	private static void pintarInforme(HiloTransferencia[] hilos, GestorTransferencias gestor) {
		int totalProcesadas = 0;
		double totalProcesado = 0;
		double totalPagado = 0;
		int totalInternas = 0;
		int totalExternas = 0;
		int totalSinSaldo = 0;

		System.out.println();
		System.out.println("========== INFORME POR HILO ==========");
		for (HiloTransferencia h : hilos) {
			System.out.println("Hilo " + h.getIdHilo() + ":");
			System.out.println("   Transferencias procesadas: " + h.getNumProcesadas());
			System.out.println("   Dinero procesado: " + redondear(h.getDineroProcesado()) + " euros");
			System.out.println("   Dinero pagado: " + redondear(h.getDineroPagado()) + " euros");
			System.out.println("   Internas: " + h.getNumInternas());
			System.out.println("   Externas: " + h.getNumExternas());
			System.out.println("   Sin saldo: " + h.getNumSinSaldo());

			totalProcesadas += h.getNumProcesadas();
			totalProcesado += h.getDineroProcesado();
			totalPagado += h.getDineroPagado();
			totalInternas += h.getNumInternas();
			totalExternas += h.getNumExternas();
			totalSinSaldo += h.getNumSinSaldo();
		}

		System.out.println("============ INFORME TOTAL ============");
		System.out.println("Total transferencias procesadas: " + totalProcesadas);
		System.out.println("Total dinero procesado: " + redondear(totalProcesado) + " euros");
		System.out.println("Total dinero pagado: " + redondear(totalPagado) + " euros");
		System.out.println("Total internas: " + totalInternas);
		System.out.println("Total externas: " + totalExternas);
		System.out.println("Total sin saldo: " + totalSinSaldo);
		System.out.println("Quedan " + redondear(gestor.getSaldo()) + " euros en la cuenta");
		System.out.println("Fin del proceso.");
	}

//	  Redondea un número a 2 decimales.
	 
	private static double redondear(double numero) {
		return Math.round(numero * 100) / 100.0;
	}
}