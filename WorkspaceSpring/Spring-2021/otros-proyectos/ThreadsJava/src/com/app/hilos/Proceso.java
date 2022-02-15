package com.app.hilos;

public class Proceso extends Thread
{
	public Proceso(String msg)
	{
		super(msg);
	}
	
	public void run()
	{
//		for(int i = 0; i<=20000;i++)
//		{
//			//getname permite mostrar el nombre del proceso
//			System.out.println(this.getName());
//		}
		
		try 
		{
			
			int minutos = 0;
			int segundos = 0;
			
			for(minutos = 0;minutos<60;minutos++)
			{
				for(segundos=0;segundos<60;segundos++)
				{
					System.out.println(minutos+":"+segundos);
					Thread.sleep(500);
				}
				System.out.println(minutos+":"+segundos);
			}
			
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
	}

}
