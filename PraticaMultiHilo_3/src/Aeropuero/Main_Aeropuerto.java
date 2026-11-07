package Aeropuero;

public class Main_Aeropuerto {

	public static void main(String[] args) {

		Pista pista=new Pista();
		
		//Hilo reloj para mostrar que salen cada 2 segundos
		Reloj reloj=new Reloj();
		reloj.start();

		for(int i=0;i<20;i++) {
			try {
				//se crearan 20 aviones
				Aeroplano aeroplano=new Aeroplano(pista);
				aeroplano.start();
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
