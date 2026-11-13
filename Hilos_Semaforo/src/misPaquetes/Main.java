package misPaquetes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
/*
 * para que un hilo no se comvierta en egoista se soluciona poniendo un sleep en el hilo
 * 
 * 
 * 
 * */
	public static void main (String args[]) {
		Semaphore miSemaforo = new Semaphore(2); //pasando hilos de dos en dos
		Cola micola = new Cola(miSemaforo); //todos los hilos deben ver el mismo semaforo
		
		//un pool de hilos cuando uno acaba empieza otro
		//el examen sera con executors
		ExecutorService miExecutor = Executors.newFixedThreadPool(10);
		
		for(int i=0; i<10; i++ ) {			
			miExecutor.submit(new Hilo(micola));			
		}
		
		miExecutor.shutdown();
	}
}