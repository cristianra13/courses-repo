package classes.estados_hilo;

public class Principal {

	public static void main(String[] args) 
	{
		// tenemos un hilo en su primer estado que es: new
		HiloProceso hilo1 = new HiloProceso();
		HiloProceso hilo2  = new HiloProceso();
		
		// estado ejecutable (runnable)
		hilo1.start();
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		
		hilo2.start();
		// hilo2.stop();
		hilo2.interrupt();
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Finaliza ejecución hilos");
	}

}
