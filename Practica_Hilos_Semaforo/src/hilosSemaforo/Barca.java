package hilosSemaforo;

public class Barca implements Runnable{

	private int idBarca;
	//ponerle prioridad a una barca
	
	private Isla isla;
	
	public Barca() {
		idBarca++;
	}

	
	@Override
	public void run() {
		System.out.println("La balsa actual es la Balsa " + this.idBarca);
		isla.rescatarNaufragos();

		
	}

	public int getIdBarca() {
		return idBarca;
	}

}
