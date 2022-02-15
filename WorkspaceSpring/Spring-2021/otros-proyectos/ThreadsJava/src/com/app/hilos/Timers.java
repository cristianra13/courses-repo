package com.app.hilos;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Timers 
{
	Timer tiempo = new Timer();
	TimerTaskTest timer = new TimerTaskTest(); 
	
	public void tiempo()
	{
		// con la siguiente linea , se ejecuta una tarea de TimerTask cada segundo con retraso de tiempo 0
		//tiempo.schedule(timerTask,0,1000);
		
		/**
		 * 	el metodo tiempo se estaba llamando desde el main, dentra y crea una tarea que se va ejecutar cada segundo
		 * Como parametro se envia el objeto timer que se instancio de la clase TimerTaskTest.
		 * eL CUAL SE LLAMA Y VA A LLEGAR A SU METODO RUN()
		 * */
		tiempo.schedule(timer,0,1000);
		
	}
	
	
	
	TimerTask timerTask = new TimerTask() 
	{
		@Override
		public void run() 
		{
			System.out.println("Tiempo de ejecución:"+ new Date());
			//tiempo();
		}
	};
	
}
