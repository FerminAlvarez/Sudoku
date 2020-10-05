package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;

public class Juego {
	int nivel = 1; //        1    -    70% completado    -    2    50 % compleatado    - 3 30% completado
	Celda[][] tablero;
	public Juego(JPanel Panel){
		tablero = new Celda[9][9];
		for(int fila = 0; fila<9; fila++) {
			for(int columna = 0; columna<9;columna++) {
				Celda celda = new Celda();
				tablero[fila][columna] = celda;
				Panel.add(celda.getCeldaGrafica().getComboBox());
			}
		}
		inicializarJuego();
	}
	
	private void inicializarJuego() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Fermin\\Desktop\\Universidad\\2020 - 2do Cuatrimestre\\TDP\\Proyecto 2\\SUDOKU\\EstadoInicial.txt"));
			String lineaArchivo[];
			for (int j = 0; j < 9 ; j++) {
				lineaArchivo = br.readLine().split(" ");
				for (int i = 0; i<lineaArchivo.length ; i++) {
					if(esCeldaInicial()) {
						tablero[j][i].setValor(Integer.parseInt(lineaArchivo[i]));
						tablero[j][i].setCeldaInicial();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	private boolean esCeldaInicial() {
		boolean toReturn = false;
		Random random = new Random();
		int valor = random.nextInt(2);
		if(valor == 1)
			toReturn = true;
		return toReturn;
	}
	
	
}
