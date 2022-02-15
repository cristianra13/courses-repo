package com.app.hilos;

public class Principal {

	public static void main(String[] args) 
	{
		Thread hilo = new Proceso("Primer Proceso");
		Thread hilo2 = new Proceso("Segundo Proceso");
		Timers timers = new Timers();
		
		//Inicializa los hilo
		//hilo.start();
		//hilo2.start();
		
		
		//aca se llama al metodo tiempo de la clase Timers
		timers.tiempo();
		
	}

}
