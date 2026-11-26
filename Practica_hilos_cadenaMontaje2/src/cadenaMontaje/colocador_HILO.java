package cadenaMontaje;

import java.util.Random;

public class colocador_HILO extends Thread{
	//hilo que coloca los 3 productos - SOLO EN POSICIONES LIBRES
	
	
	//si no tiene espacio para colocar espera a que los Empaquetadores recogan
	
	
	//colocamos un producto cada cierto tiempo entre 0 y 250ms
	
	
	//el producto a colocar sera aletorio (son numeros pueden ser 1,2 o 3)
	
	
	//cada vez que el robot coloca un producto lo cuenta ++
	
	
	//el colocador debe dar 20 iteraciones, despues se para todo
	private static final Random RANDOM = new Random();

	
	private Estanteria_Comun estanteria;
	private int contador=0;
	
	public colocador_HILO(Estanteria_Comun estanteria) {
		this.estanteria=estanteria;
	}
	
	
	@Override
	public void run() {
		int numeroColocar=(int) RANDOM.nextLong(1,4);
		
		estanteria.colocar(numeroColocar);
		System.out.println("Produciendo tipo "+numeroColocar);
		
		
	}
	
	
}
