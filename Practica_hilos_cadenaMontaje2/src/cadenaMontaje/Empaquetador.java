package cadenaMontaje;

public class Empaquetador extends Thread {

	private int producto_a_recoger;
	private Estanteria_Comun estanteria;
	private int empaquetados = 0; // total de empaquetados

	public Empaquetador(int producto, Estanteria_Comun estanteria) {
		this.producto_a_recoger = producto;
		this.estanteria = estanteria;
	}

	@Override
	public void run() {
		try {
			int numRecogido = 0;
			while(true) {
				System.out.println("Soy el empaquetador tipo " + producto_a_recoger);
				numRecogido = estanteria.recoger(producto_a_recoger); // producto_a_recoger es el producto de cada
				System.out.println("\nRecogiendo producto " + numRecogido + "\n");
			}
//			Thread.sleep(1000);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
