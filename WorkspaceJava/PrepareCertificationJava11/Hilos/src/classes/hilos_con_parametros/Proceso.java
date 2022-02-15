package classes.hilos_con_parametros;

public class Proceso extends Thread
{
	private int numInt;
	
	public Proceso(String nombreHilo)
	{
		super(nombreHilo);
	}
	
	@Override
	public void run()
	{
		for(int i = 0; i  <= numInt; i++)
		{
			System.out.println(i + " " + this.getName());
		}
		
		System.out.println();
	}
	
	// método que recibe el valor a través de parametros
	public void valorCondicion(int valor)
	{
		this.numInt = valor;
	}
}
