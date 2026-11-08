package proyecto_Nominas;
/*
 * Transferencia ---- Representa una línea del fichero de transferencias.
 */
public class Transferencia {

	private String cuenta;
	private double importe;

	//constructor 
	public Transferencia(String cuenta, double importe) {
		this.cuenta = cuenta;
		this.importe = importe;
	}

	//GETTERs
	
	public String getCuenta() {
		return cuenta;
	}

	public double getImporte() {
		return importe;
	}
	/*
	 *  true si es interna(empieza por 1)
	 *  false si es externa (empieza por 2).
	 */
	public boolean esInterna() {
		return cuenta.charAt(0) == '1';
	}

	/*
	 *reescribimos la salida del objeto Transferencia
	 */
	@Override
	public String toString() {
		return cuenta + ";" + importe;
	}
}