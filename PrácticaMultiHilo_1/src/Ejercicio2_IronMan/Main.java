package Ejercicio2_IronMan;

public class Main {

	public static void main(String[] args) {
		
		
		IronMan pedro=new IronMan("Pedro", 6, 20, 1000);
		IronMan juan=new IronMan("Juan", 7, 18, 2000);
		IronMan vicente=new IronMan("Vicente", 6, 30, 1000);
		
		pedro.start();
		juan.start();
		vicente.start();

	}

}
