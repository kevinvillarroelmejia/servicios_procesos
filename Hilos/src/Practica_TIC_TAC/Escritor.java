package Practica_TIC_TAC;

public class Escritor extends Thread{
	
	private String palabra;

	public Escritor(String palabra) {
		this.palabra=palabra;
	}
	
	//metodo que imprime 20 veces
	public void decirPalabra() {
		try {
			for(int i=0;i<19;i++) {
			System.out.println(this.palabra);
			this.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		this.decirPalabra();
	}
	
	
	//
}
