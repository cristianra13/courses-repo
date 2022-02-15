package classes.pildoras_hilos.banco_hilos;


public class EjecucionTransferencias implements Runnable 
{
	private Banco banco;
	private int cuentaOrigen;
	private double cantidad;
	
	public EjecucionTransferencias(Banco banco, int cuentaOrigen, double cantidad) 
	{
		this.banco = banco;
		this.cuentaOrigen = cuentaOrigen;
		this.cantidad = cantidad;
	}

	/*
	 * Método que arranca cada transferencia
	 */
	@Override
	public void run() 
	{
		while(true)
		{
			int cuentaDestino  = (int) (100*Math.random());
			
			double cantidad = this.cantidad * Math.random();
			
			
			
			try 
			{
				this.banco.transferencia(this.cuentaOrigen, cuentaDestino, cantidad);
				Thread.sleep(10);
			} 
			catch (InterruptedException e) 
			{
				System.out.println("Error en la ejecución del hilo " + e);
			}
		}
	}
	
}
