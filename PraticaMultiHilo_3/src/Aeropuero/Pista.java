package Aeropuero;

public class Pista {
	
	private Aeroplano aeroplano;
	
	
	public synchronized void despegue(Aeroplano aeroplano) {
		try {
			System.out.println("Aeroplano de tipo "+aeroplano.getTipoAeroplano()+" con numero "+aeroplano.getName()+" entra en la pista de despegue...");
			if(aeroplano.getTipoAeroplano().equalsIgnoreCase("AVION")) {
				Thread.sleep(3000);
			}else {
				Thread.sleep(2000);
			}
			System.out.println("Aeroplano de tipo: "+aeroplano.getTipoAeroplano()+" con numero "+aeroplano.getName()+" ha despegado");
		} catch (Exception e) {
			e.getMessage();
		}		
	}
	
	
	
}
