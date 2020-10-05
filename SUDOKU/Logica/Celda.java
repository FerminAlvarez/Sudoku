package Logica;

public class Celda {
	private int valor = 0;
	private CeldaGrafica celdaGrafica;
	private int fila;
	private int columna;
	
	
	public Celda() {
		this.celdaGrafica = new CeldaGrafica(this);
	}
	
	public CeldaGrafica getCeldaGrafica() {
		return this.celdaGrafica;
	}
	
	
	public void setValor(int valor) {
		this.valor= valor;
		celdaGrafica.setValor(valor);
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setCeldaInicial() {
		celdaGrafica.setCeldaInicial();
	}
	
	public void setPosicion(int fila, int columna) {
		this.fila= fila;
		this.columna = columna;
		
	}

}
