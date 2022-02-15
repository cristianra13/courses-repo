package classes.pildoras_hilos;

import java.awt.Component;

public class PelotaHilos implements Runnable 
{
	private Pelota pelota;
	private Component component;
	
	public PelotaHilos(Pelota pelota, Component component)
	{
		this.pelota = pelota;
		this.component = component;
	}
	
	@Override
	public void run()
	{
		// obtenemos el estado del hilo
		System.out.println("Estado inicial: " +Thread.currentThread().isInterrupted());
		
		// for (int i=1; i<=3000; i++){
		// mientras no sea interrumpido el hilo, continua su ejecución
		// while(!Thread.interrupted()) {
		while(!Thread.currentThread().isInterrupted()) {
			
			pelota.mueve_pelota(component.getBounds());
			
			component.paint(component.getGraphics());
			
			try 
			{
				// sleep bloquea el hilo, si se trata de detener, lanza la excepcion
				Thread.sleep(2);
			} 
			catch (InterruptedException e) 
			{
//				System.out.println("Hilo bloqueado, imposible su interrupción: " + e);
				// System.exit(0);
				
				//Detiene el hilo
				Thread.currentThread().interrupt();
			}
			
		}
		
		System.out.println("Estado final: " +Thread.currentThread().isInterrupted());
	}
}
