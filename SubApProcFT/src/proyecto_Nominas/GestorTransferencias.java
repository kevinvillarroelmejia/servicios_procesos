package proyecto_Nominas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 * gestorTransferencias - Clase COMPARTIDA por todos los hilos
 * guarda la colección de transferencias, el saldo de la cuenta de la empresa
 * casi todos los metodos son -synchronized- por que solo un hilo puede estar dentro a la vez
 *
 * eescribe en transferencias_internas, transferencias_externas y transferencias_sin_saldo
 */
public class GestorTransferencias {

	// nombres de los ficheros de salida
	public static final String FICHERO_INTERNAS = "transferencias_internas";
	public static final String FICHERO_EXTERNAS = "transferencias_externas";
	public static final String FICHERO_SIN_SALDO = "transferencias_sin_saldo";

	private List<Transferencia> transferencias;
	private int siguiente = 0; // posición de la próxima transferencia a repartir
	private double saldo;

	private FileWriter fwInternas;
	private FileWriter fwExternas;
	private FileWriter fwSinSaldo;

	//c
	public GestorTransferencias(List<Transferencia> transferencias, double saldo, String ruta)
			throws IOException {
		this.transferencias = transferencias;
		this.saldo = saldo;//saldo de la empresa para pagar las nominas
		this.fwInternas = new FileWriter(new File(ruta, FICHERO_INTERNAS));
		this.fwExternas = new FileWriter(new File(ruta, FICHERO_EXTERNAS));
		this.fwSinSaldo = new FileWriter(new File(ruta, FICHERO_SIN_SALDO));
	}

	/*
	 * Da al hilo que la pide la SIGUIENTE transferencia SIN procesar.
	 */
	public synchronized Transferencia siguienteTransferencia() {
		Transferencia t = null;
		if (siguiente < transferencias.size()) {
			t = transferencias.get(siguiente);
			siguiente++;
		}
		return t;//LA SIGUIENTE TRANSFERENCIA O NULL SI SE ACABARON
	}

	/*
	 * Intenta pagar la transferencia restando su importe del saldo de la empresa.
	 * devuelve true si había saldo y se ha pagado
	 * false si no había saldo.
	 */
	public synchronized boolean pagar(Transferencia transferencia) {
		boolean pagada = false;
		if (saldo >= transferencia.getImporte()) {
			saldo = saldo - transferencia.getImporte();//pagamos la nomina con el sueldo de la empresa
			pagada = true;
		}
		return pagada;
	}

	/*
	 * guarda una transferencia interna en su fichero
	 */
	public synchronized void grabarInterna(Transferencia transferencia) throws IOException {
		fwInternas.write(transferencia + "\n");
		fwInternas.flush();
	}

	/*
	 * graba una transferencia externa en su fichero
	 */
	public synchronized void grabarExterna(Transferencia transferencia) throws IOException {
		fwExternas.write(transferencia + "\n");
		fwExternas.flush();
	}

	
//	  Graba una transferencia sin saldo en su fichero.
	 
	public synchronized void grabarSinSaldo(Transferencia transferencia) throws IOException {
		fwSinSaldo.write(transferencia + "\n");
		fwSinSaldo.flush();
	}

	//getter del saldo de la cuenta
	public synchronized double getSaldo() {
		return saldo;
	}

	
//	 Cierra los 3 ficheros, se llama cuando todos los hilos han terminado
	public void cerrarFicheros() throws IOException {
		fwInternas.close();
		fwExternas.close();
		fwSinSaldo.close();
	}
}