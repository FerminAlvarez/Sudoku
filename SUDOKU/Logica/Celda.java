package Logica;

public class Celda {
	private int valor = 0;
	private CeldaGrafica celdaGrafica;
	
	public Celda() {
		this.celdaGrafica = new CeldaGrafica(this);
	}
	
	public Celda(boolean esCeldaInicial,int valor) {
		this.celdaGrafica = new CeldaGrafica(esCeldaInicial,valor);
	}
	
	public CeldaGrafica getCeldaGrafica() {
		return this.celdaGrafica;
	}
	
	
	public void actualizarValor(int valor) {
		this.valor= valor;
	}

}
