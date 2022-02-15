package classes.pildoras_hilos;

public class SincronizandoHilos {

	public static void main(String[] args) throws InterruptedException 
	{
		HilosVarios hilo1 = new HilosVarios("Hilo 1");
		HilosVarios2 hilo2 = new HilosVarios2(hilo1);
		
		// hilo1
		hilo1.start();
		
		// hilo2
		hilo2.start();
		
		System.out.println("Terminadas las tareas");
		
	}

}


class HilosVarios extends Thread
{
	
	public HilosVarios(String nameThread)
	{
		super(nameThread);
	}

	@Override
	public void run() 
	{
		for(int i = 0; i < 15; i++)
		{
			System.out.println("Ejecutando " + this.getName());
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



class HilosVarios2 extends Thread
{
	
	private Thread hilo;
	
	public HilosVarios2(Thread hilo)
	{
		this.hilo = hilo;
	}

	@Override
	public void run() 
	{
		// no se comienza la tarea del for, hasta que el hilo pasado por parametro no termina su tarea
		try 
		{
			this.hilo.join();
		} 
		catch (InterruptedException e1) 
		{
			e1.printStackTrace();
		}
		
		for(int i = 0; i < 15; i++)
		{
			System.out.println("Ejecutando " + getName());
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