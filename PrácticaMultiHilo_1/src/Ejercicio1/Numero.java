package Ejercicio1;

public class Numero extends Thread {

	//el atributo a pasar es libre, en mi caso voy  a pasarle un boolean
	//si es true es par
	//si es false es impar
	private boolean parImpar;
	
	public Numero(boolean parImpar) {
		this.parImpar=parImpar;
	}
	
	
	public void esParImpar() {
		if(parImpar) {
			for(int i=0;i<=10;i+=2) {
				System.out.println(i);
			}
		}
		if(!parImpar) {
			for(int i=0;i<=10;i++) {
				if(i%2!=0){
					System.out.println(i);
				}
			}
		}
	}
	
	@Override
	public void run() {
		this.esParImpar();
	}
}
