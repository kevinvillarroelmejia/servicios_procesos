package Practica_TIC_TAC;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Escritor tic=new Escritor("TIC");
		Escritor tac=new Escritor("TAC");
		
		Escritor toc=new Escritor("TOC");
		
			tic.start();
			tac.start();
		
			//numero maximo de hilos en PARALELO que vamos a usar
		ExecutorService miEjecutor= Executors.newFixedThreadPool(2);
		
		miEjecutor.submit(tic);
		miEjecutor.submit(tac);
		
		//este se quedaria esperando hasta que acaben los de arriba
		//por que el maximo de hilos de paralelo es 2
		miEjecutor.submit(toc);
		
		//esto es como el close es para el cerrar el pool
		miEjecutor.shutdown();
		//mientras no este terminado
//		while(!miEjecutor.isTerminated()) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		//si la siguiente linea llega antes que el shutdown el Main sigue hacia abajo
		miEjecutor.awaitTermination(1, TimeUnit.HOURS);
		
		System.out.println("Hemos acabado");
	}

}
