package paquete;

public class Hilo extends Thread {
	private Contador contador;
	
	public Hilo(Contador miContador) {
		this.contador=miContador;
	}
	
	@Override
	public void run() {
		int i=0;
		while(i<=5000) {
			contador.sumar();
			i++;
		}
	}
}
