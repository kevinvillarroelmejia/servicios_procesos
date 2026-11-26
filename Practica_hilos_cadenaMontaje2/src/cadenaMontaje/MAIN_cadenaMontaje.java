package cadenaMontaje;

public class MAIN_cadenaMontaje {

	public static void main(String[] args) {

		Estanteria_Comun estanteria=new Estanteria_Comun();
		
		colocador_HILO colocador=new colocador_HILO(estanteria);
		
		Empaquetador empaquetador1=new Empaquetador(1, estanteria);
		Empaquetador empaquetador2=new Empaquetador(2, estanteria);
		Empaquetador empaquetador3=new Empaquetador(3, estanteria);
		
		colocador.start();
		empaquetador1.start();
		empaquetador2.start();
		empaquetador3.start();

		
	}

}
