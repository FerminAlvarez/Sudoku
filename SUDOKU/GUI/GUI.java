package GUI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Logica.Celda;
import Logica.Juego;

import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GUI {
	
	
	private JFrame frame;
	private Juego juego;
	private JLabel JLabel0;
	private JLabel JLabel1;
	private JLabel JLabel2;
	private JLabel JLabel3;
	private Timer timer;
	private int tiempo = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 703);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel Panel1 = new JPanel();
		Panel1.setBounds(10, 65, 617, 588);
		frame.getContentPane().add(Panel1);
		Panel1.setLayout(new GridLayout(9, 9, 9, 9));
		
		juego = new Juego(Panel1,this);
		
		JLabel0 = new JLabel("");
		JLabel0.setBounds(175, 11, 46, 43);
		JLabel0.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero0.png")));
		frame.getContentPane().add(JLabel0);
		
		JLabel1 = new JLabel("");
		JLabel1.setBounds(220, 11, 46, 43);
		JLabel1.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero0.png")));
		frame.getContentPane().add(JLabel1);
		
		JLabel2 = new JLabel("");
		JLabel2.setBounds(289, 11, 46, 43);
		JLabel2.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero0.png")));
		frame.getContentPane().add(JLabel2);
		
		JLabel3 = new JLabel("");
		JLabel3.setBounds(336, 11, 46, 43);
		JLabel3.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero0.png")));
		frame.getContentPane().add(JLabel3);
		iniciarTiempo(1000);

	}
	
	private void iniciarTiempo(int milisegundos) {
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				establecerTiempo(tiempo++);
			}
			
		};
		timer.schedule(task, milisegundos,milisegundos);
	}
	
	private void establecerTiempo(int tiempo) {
		int unidadMayor  = tiempo / 60;
		int unidadMenor =  tiempo % 60;
		JLabel2.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero"+unidadMenor/10+".png")));
		JLabel3.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero"+unidadMenor%10+".png")));
		JLabel1.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero"+unidadMayor%10+".png")));
		JLabel0.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/Numero"+unidadMayor/10+".png")));
	}
	
	public int getTiempoYDetener() {
		timer.cancel();
		return tiempo;
		
	}
	
}
