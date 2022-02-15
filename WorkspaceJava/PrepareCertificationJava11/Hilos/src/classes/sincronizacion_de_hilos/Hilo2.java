package classes.sincronizacion_de_hilos;

public class Hilo2 extends Thread 
{
	@Override
	public void run() 
	{
		for(int i = 0; i <= 5; i++)
		{
			System.out.print("E");
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	} 

}
