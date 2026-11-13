package proyecto_Nominas;

import java.io.IOException;

/*
 * HiloTransferencia - Hilo que procesa transferencias
 * Mientras queden transferencias, pide la siguiente al gestorTransferencia
 *
 * Constructor: recibe el número de hilo y el gestor compartido (el MISMO objeto para todos).
 */
public class HiloTransferencia implements Runnable {

	private int idHilo;
	private GestorTransferencias gestor;

	// Cuentas de este hilo (solo las toca este hilo, por eso no hace falta
	// sincronizarlas)
	private int numProcesadas = 0;
	private double dineroProcesado = 0;
	private double dineroPagado = 0;
	private int numInternas = 0;
	private int numExternas = 0;
	private int numSinSaldo = 0;

	/*
	 * Crea el hilo. Parámetros: número del hilo y gestor compartido de
	 * transferencias.
	 */
	public HiloTransferencia(int idHilo, GestorTransferencias gestor) {
		this.idHilo = idHilo;
		this.gestor = gestor;
	}

//	  el hilo procesa transferencias hasta que el gestor devuelve null==no quedan.
	@Override
	public void run() {
		try {
			Transferencia t = gestor.siguienteTransferencia();
			while (t != null) {
				procesar(t);
				t = gestor.siguienteTransferencia();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Hilo " + idHilo + ": error al grabar en fichero: " + e.getMessage());
		}
	}

//	 Procesa la transferencia, la paga si hay saldo y la graba en su fichero.
	private void procesar(Transferencia transferencia) throws InterruptedException, IOException {
		numProcesadas++;
		dineroProcesado += transferencia.getImporte(); // cuenta aunque no haya saldo, porque se ha procesado

		if (gestor.pagar(transferencia)) {
			dineroPagado += transferencia.getImporte();
			System.out.println("Hilo " + idHilo + " - Cuenta: " + transferencia.getCuenta()
					+ " - Actualizamos el saldo de la cuenta con el importe: " + transferencia.getImporte() + " euros");
			if (transferencia.esInterna()) {
				Thread.sleep(1000); // simula las validaciones del banco (fuera del synchronized)
				gestor.grabarInterna(transferencia);
				numInternas++;
				System.out.println("Hilo " + idHilo + " - Grabamos transferencia interna. Cuenta " + transferencia.getCuenta());
			} else {
				gestor.grabarExterna(transferencia);
				numExternas++;
				System.out.println("Hilo " + idHilo + " - Grabamos transferencia externa. Cuenta " + transferencia.getCuenta());
			}
		} else {
			gestor.grabarSinSaldo(transferencia);
			numSinSaldo++;
			System.out.println("Hilo " + idHilo + " - Sin saldo para la cuenta " + transferencia.getCuenta() + " (importe "
					+ transferencia.getImporte() + " euros)");
		}
	}

	// Getters
	public int getIdHilo() {
		return idHilo;
	}

	public int getNumProcesadas() {
		return numProcesadas;
	}

	public double getDineroProcesado() {
		return dineroProcesado;
	}

	public double getDineroPagado() {
		return dineroPagado;
	}

	public int getNumInternas() {
		return numInternas;
	}

	public int getNumExternas() {
		return numExternas;
	}

	public int getNumSinSaldo() {
		return numSinSaldo;
	}
}