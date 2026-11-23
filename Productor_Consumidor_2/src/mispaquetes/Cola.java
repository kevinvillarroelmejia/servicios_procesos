package mispaquetes;

public class Cola {
    private int numero;
    private boolean disponible = false;//inicialmente cola vacia

    public synchronized int get() { //SE RECOGE DE LA COLA
    	  while (!disponible) {
    	    try {
    	          wait();
    	    } catch (InterruptedException e) { }
    	  }
    	  //visualize valor
    	  disponible = false;
    	  notify();
    	  return numero;
    	}

    public synchronized void put(int valor) { //SE AGREGA A LA COLA
    	  while (disponible){
    	    try {
    	          wait();
    	    } catch (InterruptedException e) { }
    	  }
    	  numero = valor;
    	  disponible = true;
    	  //visualiza valor
    	  notify();
    	}          
}
