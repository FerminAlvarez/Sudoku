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
		imagenes.setFocusable(false);
		imagenes.setModel(modelo);
		iniciarOyente();
		
	}
	
	public CeldaGrafica(boolean celdaInicial, int valor) {
		//Creamos un modelo para poner imagenes en un combobox
		DefaultComboBoxModel<Icon> modelo = new DefaultComboBoxModel<Icon>();
		if(celdaInicial) {
			modelo.addElement(new ImageIcon(this.getClass().getResource("/Imagenes/Numero"+valor+".png")));
			imagenes.setEnabled(false);
		}else {
			//Creamos el combobox con las 9 imagenes
			for (int i = 1; i<10; i++) {
				modelo.addElement(new ImageIcon(this.getClass().getResource("/Imagenes/Numero"+i+".png")));
			}
		}
		imagenes.setFocusable(false);
		imagenes.setModel(modelo);
		iniciarOyente();
	}
	
	public int getValor() {
		return imagenes.getSelectedIndex();
	}
	public JComboBox<Icon> getComboBox(){
		return imagenes;
	}
	
	private void iniciarOyente() {
		imagenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				celda.actualizarValor(imagenes.getSelectedIndex()+1);
				System.out.println(imagenes.getSelectedIndex()+1);
				imagenes.setBackground(Color.orange);
			}
		});
	}
	
}
