package classes.pildoras_hilos.banco_hilos;

public class BancoSinSincronizar 
{
	public static void main(String[] args) 
	{
		Banco banco = new Banco();
		
		for(int i = 0; i < 100; i++)
		{
			Thread hilo = new Thread(new EjecucionTransferencias(banco, i, 2000));
			hilo.start();
			
		}
	}
}
