package Ejercicio5_ElArtificieroLabomba;

public class Main {

	public static void main(String[] args) {
		
		//El main ve los hilos por que los crea
		
		Tarea artificiero=new Tarea(false);
		Tarea contador=new Tarea(true);
		
		Thread hiloArtificiero=new Thread(artificiero);
		Thread hiloContador=new Thread(contador);
		
		hiloArtificiero.start();
		hiloContador.start();
		
		//Preguntar si estan vivos los hilos
		while(hiloArtificiero.isAlive() &&
				hiloContador.isAlive()) {
			try {
				Thread.sleep(10);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//aqui uno de los dos hilos termino
		//interrumpiendo el hilo que sigue VIVO
		if(hiloArtificiero.isAlive()) {
			hiloArtificiero.interrupt();
		}else {
			hiloContador.interrupt();
		}
		
	}

}
