package Aeropuero;

public class Reloj extends Thread {

	public Reloj() {

	}

	@Override
	public void run() {
		try {
			for(int i=0;i<65;i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
