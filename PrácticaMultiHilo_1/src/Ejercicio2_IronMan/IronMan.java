package Ejercicio2_IronMan;

public class IronMan extends Thread{
	
	private static final int DISTANCIA_BICI=180;
	private static final int DISTANCIA_MARATHON=42;
	private static final int DISTANCIA_NADAR=4000;

	
	
	private String nombre;
	private int velocidadCorrer;
	private int velocidadBici;
	private int velocidadNadar;
	
	public IronMan(String nombre,int correr,int bici, int nadar) {
		this.nombre=nombre;
		this.velocidadCorrer=correr;
		this.velocidadBici=bici;
		this.velocidadNadar=nadar;
	}
	
	
	public void marathon() {
		try {
			System.out.println(this.nombre+" comineza la marathon");
			Thread.sleep(DISTANCIA_MARATHON*1000/velocidadCorrer);
			System.out.println(this.nombre+" ha acabado la marathon");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void bici() {
		try {
			System.out.println(this.nombre+" comienza la prueba en bici");
			Thread.sleep(DISTANCIA_BICI*1000/velocidadBici);
			System.out.println(this.nombre+" ha acabado la prueba en bici");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void nadar() {
		try {
			System.out.println(this.nombre+" comineza a nadar");
			Thread.sleep(DISTANCIA_NADAR*1000/velocidadNadar);
			System.out.println(this.nombre+" ha termiando de nadar");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		this.marathon();
		this.bici();
		this.nadar();
	}
	
	
	
	

}
