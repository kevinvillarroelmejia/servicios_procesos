package mispaquetes;

public class Cola { //clase COMUN
    private int numero;
    private boolean disponible = false;//inicialmente cola vacia

    public synchronized int get() { //CONSEGUIR NUMERO
    	if(disponible) {
    		disponible = false; //se pone cola vacía    		
            return numero;      //se devuelve
    	} else {
    		return -1;
    	}	   			    	 
    }

    public synchronized void put(int valor) {   //PONER NUMERO
        numero = valor;    //coloca valor en la cola 
        disponible = true; //disponible para consumir, cola llena        
    }
}