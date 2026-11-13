package Aeropuero;
import java.util.Random;
public class Aeroplano extends Thread{
	private static final Random RANDOM = new Random();
	
	private String tipoAeroplano;
	private Pista pista;
	private int numeroHilo;
	
	public Aeroplano(Pista pista) {
		this.pista=pista;
		numeroHilo++;
		
		int numIntento = (int) RANDOM.nextLong(1, 3); //1 o 2
		
		if(numIntento==1) {//si es 1
			this.tipoAeroplano="AVION";
	        this.setPriority(Thread.MAX_PRIORITY);

		}else {//si es 2
			this.tipoAeroplano="AVIONETA";
			 this.setPriority(Thread.MIN_PRIORITY);
		}
	}
	public String getTipoAeroplano() {
		return tipoAeroplano;
	}
	public int getNumeroHilo() {
		return numeroHilo;
	}
	@Override
	public void run() {
		System.out.println("Llega a la pista el/la "+tipoAeroplano+"con numero "+this.getName());
		pista.despegue(this);
	}
}
