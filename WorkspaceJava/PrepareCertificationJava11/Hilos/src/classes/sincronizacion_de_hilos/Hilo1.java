package classes.sincronizacion_de_hilos;

public class Hilo1 extends Thread 
{
	@Override
	public void run() 
	{
		for(int i = 0; i <= 5; i++)
		{
			System.out.print(i + ": G");
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
