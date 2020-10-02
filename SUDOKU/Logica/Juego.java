package Logica;

import javax.swing.JPanel;

public class Juego {
	Celda[][] tablero;
	public Juego(JPanel Panel){
		
		tablero = new Celda[9][9];
		for(int fila = 0; fila<9; fila++) {
			for(int columna = 0; columna<9;columna++) {
				Celda celda = new Celda();
				Panel.add(celda.getCeldaGrafica().getComboBox());
			}
		}
	}
}
