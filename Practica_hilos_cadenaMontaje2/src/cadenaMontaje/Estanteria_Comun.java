package cadenaMontaje;


public class Estanteria_Comun {
	// ningun producto se debe quedar en la estanteria

	private boolean estadoEstanteria = false;

	private int[] listaEstanteria = new int[3];// lista donde se van a poner los numeros

	public synchronized void colocar(int productoPuesto) {
		int posicion=0;
		while (estadoEstanteria) {// mientras que este disponible
			try {
				wait();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		for (int i = 0; i < listaEstanteria.length; i++) {
			if (listaEstanteria[i] == 0) {
				listaEstanteria[i] = productoPuesto;
				posicion=i;
				break;
			}
		}
			System.out.println("Coloco un producto "+productoPuesto+ " en la posicion "+posicion);
		estadoEstanteria = true;
		notifyAll();
		for(int i = 0; i < listaEstanteria.length; i++) {
			System.out.print(listaEstanteria[i]+",");
		}
	}

	//
	public synchronized int recoger(int productoEmpaquetador) {
		int numero=0;
		
		while (!estadoEstanteria) {
			System.out.println("Soy en empaquetador "+productoEmpaquetador+" y y me quedo dormido");
			try {
				wait();

			} catch (InterruptedException e) {
				e.getMessage();
			}
		}

		for(int i = 0; i < listaEstanteria.length; i++) {
			
			if(listaEstanteria[i]==productoEmpaquetador) {
				System.out.println(listaEstanteria[i]);
				System.out.println(productoEmpaquetador);
				numero=listaEstanteria[i];
				listaEstanteria[i]=0;
				break;
			}
		}
		estadoEstanteria = false;
		// visualize valor
		notifyAll();
		return numero;
	}

}
