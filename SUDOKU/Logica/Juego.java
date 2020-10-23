package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Juego {
	private Celda[][] tablero;
	private LinkedList<Celda> listaCeldasMal; //Lista que contendrá todas las celdas que contienen errores y las celdas que están vacías.
	private boolean juegoInicializado = false;
	private boolean gano;
	
	public Juego(JPanel Panel){
		tablero = new Celda[9][9];
		listaCeldasMal = new LinkedList<Celda>();
		for(int fila = 0; fila<9; fila++) {
			for(int columna = 0; columna<9;columna++) {
				Celda celda = new Celda(fila,columna,this);
				tablero[fila][columna] = celda;
				Panel.add(celda.getCeldaGrafica().getComboBox());
			}
		}
		inicializarJuego();
	}
	
	private void inicializarJuego() {
		
		
		try {
			InputStream in = Juego.class.getClassLoader().getResourceAsStream("Archivos/EstadoInicial.txt");
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
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
			br.close();
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
	
	
	private boolean contieneErroresInicializacion(int fila, int columna, int valor, int[][] tablero) throws InicializacionException {
		boolean repetido = false;
		for (int i = 0; !repetido &&  i<9; i ++) 
			repetido = tablero[i][columna]== valor && i != fila;
						
		for (int i = 0; !repetido && i<9; i ++) 
			repetido = tablero[fila][i] == valor && i != columna;
			
		//Division entera, por ejemplo 5/3 = 1,66, pero es = 1.
		int filaInicialPanel = (int) (fila/3) * 3;
		int columnaInicialPanel = (int) (columna/3) * 3;
		
		for(int i = filaInicialPanel; !repetido && i< filaInicialPanel+2;i++) 
			for(int e = columnaInicialPanel;!repetido && e< columnaInicialPanel+2;e++) 
				repetido = tablero[i][e] == valor && e != columna && i != fila;
		
		if(repetido)
			throw new InicializacionException("El archivo no contiene una solución válida.");
		return repetido;
			
	}
	
	public boolean contieneErrores(int fila, int columna, int valor) {
		boolean repetido = false;
			repetido = !(estaRepetido(fila, columna, valor).isEmpty());
			
			if(juegoInicializado) {
				//Primero lo eliminamos si estaba de la lista
				if(!repetido)
					listaCeldasMal.remove(tablero[fila][columna]);
				if(repetido || tablero[fila][columna].getValor()==-1)
					listaCeldasMal.add(tablero[fila][columna]);	
				
			}
		
		return repetido;
	}
	
	public void revisarLista() {
		LinkedList<Celda> listaErroresAuxiliar = new LinkedList<Celda>();
		LinkedList<Celda> listaErroresEnCadena = new LinkedList<Celda>();
		boolean repetido = false;
		
		//Primero revisamos para todos los que están en la lista si hubo cambios.
		for(int indiceLista = 0; indiceLista<listaCeldasMal.size(); indiceLista++) {
			Celda celda = listaCeldasMal.get(indiceLista);
			int valor = celda.getValor();
			//Si la lista está vacía siginifica que no hubo error con ninguna celda.
			repetido = !estaRepetido(celda.getFila(), celda.getColumna(), valor).isEmpty();
			if((repetido || valor == -1) && (!listaErroresAuxiliar.contains(celda))) 
				listaErroresAuxiliar.add(celda);
			
			celda.setError(repetido);
		}
		
		
		if(juegoInicializado) {
			//Como ya sabemos que la ultima celda de listas celdas mal (A) está mal, para cada celda que (A) generó problemas las agregamos a la lista y les cambiamos el estado.
			Celda ultimaCelda = listaCeldasMal.getLast();
			listaErroresEnCadena = estaRepetido(ultimaCelda.getFila(), ultimaCelda.getColumna(), ultimaCelda.getValor());
			for(int indiceLista = 0; indiceLista<listaErroresEnCadena.size(); indiceLista++) {
				Celda celda = listaErroresEnCadena.get(indiceLista);
				listaErroresAuxiliar.add(celda);
				celda.setError(repetido);
			}
		}
		
		listaCeldasMal = listaErroresAuxiliar;
		if (listaCeldasMal.isEmpty() && juegoInicializado) 
	    	gano = true;
		
	}
	
	
	/*
	 * Método que va a retorar una lista con todos los elementos que se repiten en base a esa celda, chequeando para toda la fila
	 * columna y panel.
	 */
	private LinkedList<Celda> estaRepetido(int fila, int columna, int valor) {
		LinkedList<Celda> listaAuxiliar = new LinkedList<Celda>();
		for (int i = 0;  i<9; i ++) {
			//Verificamos si está repetido y no es él mismo y no es celda vacía
			if(tablero[i][columna].getValor()== valor && i != fila && valor != -1) 
				listaAuxiliar.add(tablero[i][columna]);
		}
		
		for (int i = 0;  i<9; i ++) {
			//Verificamos si está repetido y no es él mismo y no es celda vacía
			if(tablero[fila][i].getValor()== valor &&  i != columna && valor != -1) 
				listaAuxiliar.add(tablero[fila][i]);
		}
		
		int filaInicialPanel = (fila/3) * 3;
		int columnaInicialPanel = (columna/3) * 3;
		
		for(int i = filaInicialPanel; i< filaInicialPanel+3;i++) 
			for(int e = columnaInicialPanel;e< columnaInicialPanel+3;e++) {
				//Verificamos si está repetido y no es él mismo y no es celda vacía
				if(tablero[i][e].getValor()== valor && e != columna && i != fila && valor != -1) 
					listaAuxiliar.add(tablero[i][e]);
			}
		return listaAuxiliar;
	}
	
	
	private void chequearFila(String[] arreglo) throws InicializacionException{
		if(arreglo.length!=9)
			throw new InicializacionException("Alguna linea del archivo no cuenta con el formato especificado.");
	}
	
	private boolean seEstablecioCeldaInicial(int fila, int columna,int valor) {
		boolean establecer = false;
		Random random = new Random();
		int aleatorio = random.nextInt(10);
		establecer = aleatorio <3;
		if(establecer) {
			tablero[fila][columna].setCeldaInicial();
			tablero[fila][columna].inicializarValor(valor);
		}
		return establecer;
	}
	
	private void cerrarJuego(String mensaje) {
		JOptionPane.showMessageDialog(null,mensaje);
		System.exit(0);
	}
	
	public boolean getGano() {
		return gano;
	}
	
}
