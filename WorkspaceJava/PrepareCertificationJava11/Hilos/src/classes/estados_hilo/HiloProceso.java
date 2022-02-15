package classes.estados_hilo;

public class HiloProceso extends Thread
{

	@Override
	public void run() 
	{
		for(int i = 0; i  <= 10; i++)
		{
			System.out.println(i + " " + this.getName());
			try 
			{
				// estamos heredando este método
				sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
