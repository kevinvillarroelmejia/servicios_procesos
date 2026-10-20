package Ejercicio5_ElArtificieroLabomba;

public class Main {

	public static void main(String[] args) {
		
		Tarea artificiero=new Tarea(false);
		Tarea contador=new Tarea(true);
		
		Thread hiloArtificiero=new Thread(artificiero);
		Thread hiloContador=new Thread(contador);
		
		hiloArtificiero.start();
		hiloContador.start();
		
		
		
	}

}
