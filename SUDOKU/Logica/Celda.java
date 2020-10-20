package Logica;

public class Celda {
	private Juego juego;
	private int valor = -1;
	private CeldaGrafica celdaGrafica;
	private int fila;
	private int columna;
	
	
	public Celda(int fila, int columna, Juego juego) {
		this.celdaGrafica = new CeldaGrafica(this);
		this.fila = fila;
		this.columna = columna;
		this.juego = juego;
	}
	
	public CeldaGrafica getCeldaGrafica() {
		return this.celdaGrafica;
	}
	
	
	
	//Método que solamente utilizamos en el momento de inicializar las celdas.
	public void inicializarValor(int valor) {
		this.valor= valor;
		celdaGrafica.setValor(valor);
	}
	
	public void setValor(int valor) {
		this.valor= valor;
		celdaGrafica.setearCeldaError(juego.contieneErrores(fila, columna, valor));
		juego.revisarLista();
		celdaGrafica.setValor(valor);
	}
	public void setError(boolean tieneError) {
		celdaGrafica.setearCeldaError(tieneError);
	}
	
	public int getValor() {
		return valor;
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getColumna() {
		return columna;
	}
	
	
	public void setCeldaInicial() {
		celdaGrafica.setCeldaInicial();
	}
	

}
