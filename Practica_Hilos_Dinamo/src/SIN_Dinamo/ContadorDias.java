package SIN_Dinamo;

public class ContadorDias extends Thread {
	
	private int contadorDias=0;
	
	public ContadorDias() {

	}

	@Override
	public void run() {
		try {
			for(int i=0;i<6;i++) {
				System.out.println("DIA --> "+i);
				Thread.sleep(24000); //24000ms == un dia
				contadorDias++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getContadorDias() {
		return contadorDias;
	}
}
