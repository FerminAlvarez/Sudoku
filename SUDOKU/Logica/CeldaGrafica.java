package Logica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class CeldaGrafica{
	private JComboBox<Icon> imagenes = new JComboBox<Icon>();
	private Celda celda;
	
	public CeldaGrafica(Celda celda) {
		this.celda = celda;
		//Creamos un modelo para poner imagenes en un combobox
		DefaultComboBoxModel<Icon> modelo = new DefaultComboBoxModel<Icon>();
		//Creamos el combobox con las 9 imagenes
		for (int i = 1; i<10; i++) {
			modelo.addElement(new ImageIcon(this.getClass().getResource("/Imagenes/Numero"+i+".png")));
		}
		modelo.addElement(new ImageIcon(this.getClass().getResource("/Imagenes/Vacío.png")));
		imagenes.setFocusable(false);
		imagenes.setModel(modelo);
		imagenes.setSelectedIndex(9);
		imagenes.setBackground(Color.white);
		iniciarOyente();
		
	}
	
	
	public JComboBox<Icon> getComboBox(){
		return imagenes;
	}
	
	public void setValor(int valor) {
		if(valor ==-1)
			imagenes.setSelectedIndex(9);
		else
			imagenes.setSelectedIndex(valor-1);
	}
	
	public void setCeldaInicial() {
		//Al ser celda inicial quitamos la posibilidad de que modifique el valor.
		imagenes.setEnabled(false);
	}
	
	private void iniciarOyente() {
		imagenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si la imagen es la que es una imagen vacía, entonces ponemos valor -1
				if(imagenes.getSelectedIndex() == 9)
					celda.setValor(-1);
				else 
					celda.setValor(imagenes.getSelectedIndex()+1);
				
				
			}
		});
	}
	
	public void setearCeldaError(boolean ocurrioError) {
		if(ocurrioError)
			imagenes.setBackground(Color.orange);
		else 
			imagenes.setBackground(Color.white);
	}
	
	
}
