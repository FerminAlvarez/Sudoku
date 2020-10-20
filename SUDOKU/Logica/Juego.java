package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.GUI;

public class Juego {
	private Celda[][] tablero;
	private ArrayList<Celda> listaCeldasMal; //Lista que contendrá todas las celdas que contienen errores y las celdas que están vacías.
	private boolean juegoInicializado = false;
	private GUI gui;
	
	public Juego(JPanel Panel, GUI gui){
		tablero = new Celda[9][9];
		listaCeldasMal = new ArrayList<Celda>();
		for(int fila = 0; fila<9; fila++) {
			for(int columna = 0; columna<9;columna++) {
				Celda celda = new Celda(fila,columna,this);
				tablero[fila][columna] = celda;
				Panel.add(celda.getCeldaGrafica().getComboBox());
			}
		}
		this.gui = gui;
		inicializarJuego();
	}
	
	private void inicializarJuego() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\SUDOKU\\EstadoInicial - copia.txt"));
			String lineaArchivo[];
			int tableroAuiliar[][] = new int[9][9];
			for (int fila = 0; fila < 9 ; fila++) {
				lineaArchivo = br.readLine().split(" ");
				chequearFila(lineaArchivo);
				for (int columna= 0; columna<lineaArchivo.length; columna++) {
					int valor = Integer.parseInt(lineaArchivo[columna]);
					contieneErroresInicializacion(fila, columna, valor, tableroAuiliar); //Usamos el contiene errores con una matriz auxiliar para verificar si por el momento es una solución válida.
					
					if(!seEstablecioCeldaInicial(fila,columna,valor)) // Si no se estableció como celda inicial significa que está vacío.
						listaCeldasMal.add(tablero[fila][columna]);
					tableroAuiliar[fila][columna] = valor;
				}
			}
			//Significa que por casualidad todos los elementos de la matriz fueron insertados
			if (listaCeldasMal.isEmpty()) {
		    	System.out.println(listaCeldasMal.size());
		    	cerrarJuego("El sudoku por casualidad se llenó con la solución entera.");
		    }
			
			juegoInicializado=true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			cerrarJuego("El archivo no contiene solo numeros y espacios.");
		}  catch (InicializacionException i) {
			i.printStackTrace();
			cerrarJuego(i.getMessage());
		} catch(IOException io) {
			io.printStackTrace();
			cerrarJuego("Error al abrir el archivo.");
		}
	}
	
	private boolean seEstablecioCeldaInicial(int fila, int columna,int valor) {
		boolean establecer = false;
		Random random = new Random();
		int aleatorio = random.nextInt(99);
		establecer = aleatorio < 98;
		if(establecer) {
			tablero[fila][columna].setCeldaInicial();
			tablero[fila][columna].inicializarValor(valor);
		}
		return establecer;
	}
	
	private void chequearFila(String[] arreglo) throws InicializacionException{
		if(arreglo.length!=9)
			throw new InicializacionException("Alguna linea del archivo no cuenta con el formato especificado.");
		
	}
	
	private boolean contieneErroresInicializacion(int fila, int columna, int valor, int[][] tablero) throws InicializacionException {
		boolean repetido = false;
		for (int i = 0; !repetido &&  i<9; i ++) 
			repetido = tablero[i][columna]== valor && i != fila;
		
		
		for (int i = 0; !repetido && i<9; i ++) 
			repetido = tablero[fila][i] == valor && i != columna;
			
		
		int filaInicialPanel = (fila/3) * 3;
		int columnaInicialPanel = (columna/3) * 3;
		
		for(int i = filaInicialPanel; !repetido && i< filaInicialPanel+2;i++) 
			for(int e = columnaInicialPanel;!repetido && e< columnaInicialPanel+2;e++) 
				repetido = tablero[i][e] == valor && e != columna && i != fila;
		
		if(repetido)
			throw new InicializacionException("El archivo no contiene una solución válida.");
		return repetido;
			
	}
	
	public boolean contieneErrores(int fila, int columna, int valor) {
		boolean repetido = false;
		if(valor!=0) {
			for (int i = 0; !repetido &&  i<9; i ++) 
				repetido =  tablero[i][columna].getValor()== valor && i != fila;
			
			
			for (int i = 0; !repetido && i<9; i ++) 
				repetido = tablero[fila][i].getValor()== valor &&  i != columna;
			
			
			int filaInicialPanel = (fila/3) * 3;
			int columnaInicialPanel = (columna/3) * 3;
			
			for(int i = filaInicialPanel; !repetido && i< filaInicialPanel+3;i++) 
				for(int e = columnaInicialPanel;!repetido && e< columnaInicialPanel+3;e++) 
					repetido = tablero[i][columna].getValor()== valor && e != columna && i != fila;
			
			if(juegoInicializado) {
				//Primero lo eliminamos si estaba de la lista
				listaCeldasMal.remove(tablero[fila][columna]);
				
				if(repetido || tablero[fila][columna].getValor()==-1)
					listaCeldasMal.add(tablero[fila][columna]);	
				
			    if (listaCeldasMal.isEmpty()) {
			    	System.out.println(listaCeldasMal.size());
			    	cerrarJuegoGano();
			    }
			}
		}
		
		return repetido && tablero[fila][columna].getValor()!=-1;
	}
	
	public void revisarLista() {
		for(int i = 0; i<listaCeldasMal.size(); i++) {
			Celda celda = listaCeldasMal.get(i);
			celda.setError(contieneErrores(celda.getFila(),celda.getColumna(),celda.getValor()));
		}
	}
	private void cerrarJuego(String mensaje) {
		JOptionPane.showMessageDialog(null,mensaje);
		System.exit(0);
	}
	private void cerrarJuegoGano() {
		int tiempo = gui.getTiempoYDetener();
		int unidadMayor = tiempo/60;
		int unidadMenor = tiempo%60;
		JOptionPane.showMessageDialog(null,"Felicitaciones ha logrado pasar el juego\n Tiempo:  " +unidadMayor/10+unidadMayor%10+":"+unidadMenor/10+unidadMenor%10);
		System.exit(0);
	}
}
