package Ejercicio1_AdivinarNumero;

public class Jugador extends Thread{

	private int idJugador;
	private Arbitro arbitro;
	
	public Jugador(int idJudador) {
		this.idJugador=idJudador;
	}
	public int getIdJugador() {
		return idJugador;
	}
	@Override
	public void run() {
		while(!arbitro.comprobarNumero(idJugador)) {
			
			
		}
		
	}
}
