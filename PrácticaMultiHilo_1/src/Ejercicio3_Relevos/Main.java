package Ejercicio3_Relevos;

public class Main {

	public static void main(String[] args) {
		
		Corredor pepe=new Corredor("España", "Pepe");
		Corredor maria=new Corredor("España", "Maria");
		Corredor juan=new Corredor("España", "Juan");
		Corredor marta=new Corredor("España", "Marta");
		
		//carrera
		Thread p=new Thread(pepe);
		p.start();
		try {
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread m=new Thread(maria);
		m.start();
		try {
			m.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		Thread j=new Thread(juan);
		j.start();
		try {
			j.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread mar=new Thread(marta);
		mar.start();
		try {
			mar.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("FIN DEL PROGRAMA");

	}

	
	
}
