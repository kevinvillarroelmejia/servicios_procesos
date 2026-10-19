package Ejercicio4_CarreraDeRelevos;

public class Main {

	public static void main(String[] args) {
				
		Corredor_ejer4 john=new Corredor_ejer4("EEUU", "John");
		Corredor_ejer4 kim=new Corredor_ejer4("EEUU", "Kim");
		Corredor_ejer4 mike=new Corredor_ejer4("EEUU", "Mike");
		Corredor_ejer4 Sarah=new Corredor_ejer4("EEUU", "Sarah");

		Corredor_ejer4 pepe=new Corredor_ejer4("España", "pepe");
		Corredor_ejer4 maria=new Corredor_ejer4("España", "Maria");
		Corredor_ejer4 juan=new Corredor_ejer4("España", "Juan");
		Corredor_ejer4 marta=new Corredor_ejer4("España", "Marta");

		
		Equipo españaEquipo=new Equipo("España");
		Equipo eeuu=new Equipo("E.E.U.U");
		
		eeuu.ayadirCorredor(john);
		eeuu.ayadirCorredor(kim);
		eeuu.ayadirCorredor(mike);
		eeuu.ayadirCorredor(Sarah);

		
		españaEquipo.ayadirCorredor(pepe);
		españaEquipo.ayadirCorredor(maria);
		españaEquipo.ayadirCorredor(juan);
		españaEquipo.ayadirCorredor(marta);
		
		
		Thread hiloEspaña=new Thread(españaEquipo);
		hiloEspaña.start();

		Thread hiloEEUU=new Thread(eeuu);
		hiloEEUU.start();
		
		
		
		
		
	}

}
