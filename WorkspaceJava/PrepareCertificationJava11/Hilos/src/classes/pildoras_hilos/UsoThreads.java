package classes.pildoras_hilos;

import java.awt.geom.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class UsoThreads {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame marco=new MarcoRebote();
		
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		marco.setVisible(true);

	}

}




//Movimiento de la pelota-----------------------------------------------------------------------------------------

class Pelota{
	
	// Mueve la pelota invirtiendo posición si choca con límites
	
	public void mueve_pelota(Rectangle2D limites){
		
		x+=dx;
		
		y+=dy;
		
		if(x<limites.getMinX()){
			
			x=limites.getMinX();
			
			dx=-dx;
		}
		
		if(x + TAMX>=limites.getMaxX()){
			
			x=limites.getMaxX() - TAMX;
			
			dx=-dx;
		}
		
		if(y<limites.getMinY()){
			
			y=limites.getMinY();
			
			dy=-dy;
		}
		
		if(y + TAMY>=limites.getMaxY()){
			
			y=limites.getMaxY()-TAMY;
			
			dy=-dy;
			
		}
		
	}
	
	//Forma de la pelota en su posición inicial
	
	public Ellipse2D getShape(){
		
		return new Ellipse2D.Double(x,y,TAMX,TAMY);
		
	}	
	
	private static final int TAMX=15;
	
	private static final int TAMY=15;
	
	private double x=0;
	
	private double y=0;
	
	private double dx=1;
	
	private double dy=1;
	
	
}

// Lámina que dibuja las pelotas----------------------------------------------------------------------


class LaminaPelota extends JPanel{
	
	//Añadimos pelota a la lámina
	
	public void add(Pelota b){
		
		pelotas.add(b);
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		for(Pelota b: pelotas){
			
			g2.fill(b.getShape());
		}
		
	}
	
	private ArrayList<Pelota> pelotas=new ArrayList<Pelota>();
}


//Marco con lámina y botones------------------------------------------------------------------------------

class MarcoRebote extends JFrame{
	
	public MarcoRebote(){
		
		setBounds(600,300,600,350);
		
		setTitle ("Rebotes");
		
		lamina=new LaminaPelota();
		
		add(lamina, BorderLayout.CENTER);
		
		JPanel laminaBotones=new JPanel();
		
		
		
		// creamos el boton
		arranca1 = new JButton("Hilo 1");
		
		// ponemos a la escucha el boton para cuanso sea presionado
		arranca1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evento) {
				comienza_el_juego(evento);
			}
		});
		laminaBotones.add(arranca1);
		
		
		arranca2 = new JButton("Hilo 2");
		
		// ponemos a la escucha el boton para cuanso sea presionado
		arranca2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evento) {
				comienza_el_juego(evento);
			}
		});
		
		laminaBotones.add(arranca2);
		
		
		arranca3 = new JButton("Hilo 3");
		
		// ponemos a la escucha el boton para cuanso sea presionado
		arranca3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evento) {
				comienza_el_juego(evento);
			}
		});
		
		laminaBotones.add(arranca3);
		
		
		// botones detener
		detener1 = new JButton("Detener 1");
		
		// ponemos a la escucha el boton para cuanso sea presionado
		detener1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evento) {
				detener(evento);
			}
		});
		
		laminaBotones.add(detener1);
		
		detener2 = new JButton("Detener 2");
		
		// ponemos a la escucha el boton para cuanso sea presionado
		detener2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evento) {
				detener(evento);
			}
		});
		
		laminaBotones.add(detener2);
		
		detener3 = new JButton("Detener 3");
		
		// ponemos a la escucha el boton para cuanso sea presionado
		detener3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evento) {
				detener(evento);
			}
		});
		
		laminaBotones.add(detener3);
		
		add(laminaBotones, BorderLayout.SOUTH);
	}
	
	public void ponerBoton(Container c, String titulo, ActionListener oyente){
		
		JButton boton=new JButton(titulo);
		
		c.add(boton);
		
		boton.addActionListener(oyente);
		
	}
	
	//Añade pelota y la bota 1000 veces
	
	public void comienza_el_juego (ActionEvent evento){
		
					
			Pelota pelota=new Pelota();
			
			lamina.add(pelota);
			
			// Thread hilo = new Thread(new PelotaHilos(pelota, lamina));
			Runnable runn = new PelotaHilos(pelota, lamina);
			
			if(evento.getSource().equals(arranca1)) {
				hilo1 = new Thread(runn);
				// inicio del hilo
				hilo1.start();
			}
			else if(evento.getSource().equals(arranca2)) {
				hilo2 = new Thread(runn);
				hilo2.start();
			}
			else  if(evento.getSource().equals(arranca3)) {
				hilo3 = new Thread(runn);
				hilo3.start();
			}
	}
	
	public void detener(ActionEvent evento)
	{
		if(evento.getSource().equals(detener1)) {
			hilo1.interrupt();
		}
		else if(evento.getSource().equals(detener2)) {
			hilo2.interrupt();
		}
		else  if(evento.getSource().equals(detener3)) {
			hilo3.interrupt();
		}
	}
	
	private LaminaPelota lamina;
	Thread hilo1;
	Thread hilo2;
	Thread hilo3;
	JButton arranca1, arranca2, arranca3, detener1, detener2, detener3;
	
	
}
