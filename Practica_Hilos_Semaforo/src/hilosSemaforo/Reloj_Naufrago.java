package hilosSemaforo;

public class Reloj_Naufrago extends Thread {

	public Reloj_Naufrago() {

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
