package paquete;

public class Contador {
	private int contador;
	private int contador2=0;
	
	
	
	public Contador() {
	}

	
	//un metodo es que un hilo puede pasar un hilo
	//hasta que uno no sale no puede entrar otro
	//podemos syncronizar TODO el metodo o parte de ese metodo
	public void sumar() {
		this.contador2++;
		synchronized(this) {
			this.contador++;
		}
	}
	
	public int getContador() {
		return contador;
	}
	
	public int getContador2() {
		return contador2;
	}
	
	
	
}
