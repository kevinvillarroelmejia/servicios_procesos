package paquete;

public class Main {

	public static void main(String[] args) {
		
		Contador contador1=new Contador();
		Contador contador2=new Contador();
		
		Hilo hilo1=new Hilo(contador1);
		Hilo hilo2=new Hilo(contador1);
		Hilo hilo3=new Hilo(contador1);
		Hilo hilo4=new Hilo(contador1);
		Hilo hilo5=new Hilo(contador1);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		hilo5.start();

		while(hilo1.isAlive()||
				hilo2.isAlive()||
				hilo3.isAlive()||
				hilo4.isAlive()||
				hilo5.isAlive()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(contador1.getContador());
		System.out.println(contador2.getContador());


	}

}
