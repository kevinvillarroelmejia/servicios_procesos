package Ejercicio4_CarreraDeRelevos;

import java.util.ArrayList;

public class Equipo  implements Runnable{

	private String nombrePais;
	private  ArrayList<Corredor_ejer4> listaCorredores=new ArrayList<Corredor_ejer4>();
	
	
	public Equipo(String nombrePais) {
		this.nombrePais=nombrePais;
	}
	
	public  void ayadirCorredor(Corredor_ejer4 corredor) {
		listaCorredores.add(corredor);
	}
	
	@Override
	public void run() {
		for(Corredor_ejer4 corredor:listaCorredores) {
			corredor.start();
			try {
				corredor.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
