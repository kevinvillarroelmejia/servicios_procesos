package cadenaMontaje;

public class Empaquetador extends Thread{
	//Hilos(3) que recoge de la estanteria y "empaqueta"
	//uno por tipo de producto
	
	//cada empaquetador se centra en recoger su tipo de producto(1,2 o 3) ignora los demas
	
	
	//el empaquetador tarda 100ms en recoger su pedido
	
	
	//para empaquetar tardan entre 0 y 500ms  (tiempo que tarda el hilo empaquetador en volver a intentar
	//coger otro producto).
	
	// cada empaquetador cuenta cuantos paquetes hizo
	
	private int producto_a_recoger;
	private Estanteria_Comun estanteria;
	private int contador=0;
	public Empaquetador(int producto,Estanteria_Comun estanteria) {
		this.producto_a_recoger=producto;
		this.estanteria=estanteria;
	}
	
	@Override
	public void run() {
		int numRecogido=estanteria.recoger(producto_a_recoger); //producto_a_recoger es el producto de cada Empaquetador
		contador++;
		System.out.println("Recogiendo tipo "+producto_a_recoger);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	

}
