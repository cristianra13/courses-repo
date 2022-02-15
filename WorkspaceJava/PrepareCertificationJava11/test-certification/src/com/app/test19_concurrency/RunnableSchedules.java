package com.app.test19_concurrency;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunnableSchedules {

	public static void main(String[] args) {
		new RunnableSchedules();
	}
	
	public RunnableSchedules()
	{
		// el 3 es la cantidad de cores a usar
		ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
		/*
		 * 10 es el delay cuando inicia, despues de 10 segundos se va a ejecutar
		 * 5 es el tiempo en que se va a ejecutar el hilo cada gez
		 * TimeUnit.MINUTES es como queremos controlar el tiempo, pueden ser varias opciones
		 */
		service.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);
		ExecutorService es = Executors.unconfigurableExecutorService(service);
	}
	
	
	Runnable task = () -> {
//		for(int i = 0; i < 5; i++)
//		{
//			System.out.println("now: " + LocalDateTime.now().getHour());
//		}
		
		if(LocalTime.now().getHour() == 16) {
			System.out.println("Es la hora");
		}
	};
	
}
