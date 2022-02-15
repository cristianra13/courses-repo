package com.app.hilos;

import java.util.Date;
import java.util.TimerTask;

public class TimerTaskTest extends TimerTask 
{

	//se implementa el metodo abstracto run()
	@Override
	public void run() 
	{
		/**
		 * cuando llega al metodo run().
		 * 
		 * Llama al metodo impreion(), el cual tiene como codigo, imprimir en consola la fecha
		 * */
		impresion();
	}
	
	
	public void impresion()
	{
		System.out.println("Fecha de hoy: "+ new Date());
	}

}
