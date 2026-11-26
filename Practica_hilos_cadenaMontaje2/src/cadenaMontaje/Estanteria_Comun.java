package cadenaMontaje;

import java.util.ArrayList;
import java.util.Iterator;

public class Estanteria_Comun {
	// ningun producto se debe quedar en la estanteria

	private boolean estadoEstanteria = false;

	// hacer los metodos para
	// colocar
	// conseguir

	private int[] listaEstanteria = new int[3];// lista donde se van a poner los numeros

	public synchronized void colocar(int productoPuesto) {
		boolean bandera=false; //se pudo añadir el producto a la estanteria?
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
				bandera=true;
				posicion=i;
				break;
			}
		}
		if(bandera) {
			System.out.println("Coloco un producto "+productoPuesto+ " en la posicion "+posicion);
		}
		estadoEstanteria = true;
		notify();
		for(int i = 0; i < listaEstanteria.length; i++) {
			System.out.println(listaEstanteria[i]);
		}
	}

	public synchronized int recoger(int productoEmpaquetador) {
		int numero=0;
		while (!estadoEstanteria) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
		for(int i = 0; i < listaEstanteria.length; i++) {
			if(listaEstanteria[i]==productoEmpaquetador) {
				numero=listaEstanteria[i];
				listaEstanteria[i]=0;
				break;
			}
		}
		// visualize valor
		estadoEstanteria = false;
		notify();
		return numero;
	}

}
