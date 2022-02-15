package classes.pildoras_hilos.banco_hilos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Está clase debe crear 00 cuentas corrientes y luego 
 * cargarlas con 2000 a cada una
 */
public class Banco 
{
	private final double[] cuentas;
	// private Lock lock = new ReentrantLock();
	// private Condition conditionSaldoSuficiente;
	
	public Banco()
	{
		cuentas = new double[100];
		
		for(int i = 0; i < cuentas.length; i++)
		{
			cuentas[i] = 2000;
		}
		
		/*
		 *¨	Decimos que el bloqueo debe establecerse en base a una condición -> conditionSaldoSuficiente
		 */
		// conditionSaldoSuficiente = lock.newCondition();
	}
	
	public synchronized void transferencia(int cuentaOrigen, int cuentaDestino, double cantidad) throws InterruptedException
	{
//		try 
//		{
			// Bloqueamos todo el metodo 
			// lock.lock();
			
			// evalua que el saldo no sea inferior a la tranferencia
			while(cuentas[cuentaOrigen] < cantidad) 
			{
//				/*
//				 * LE decimos que mientras la condicion sea verdad,
//				 * el hilo se mantenga a la espera.
//				 * 
//				 * Ademas de estár a la espera se libera, por lo cual,
//				 * entra otro hilo
//				 */
//				// conditionSaldoSuficiente.await();
				
				// el hilo se pone a la espera
				wait();
			}
			
			// hilo que hace la transferencia
		System.out.println(Thread.currentThread());
		
		// descontamos del saldo la cantidad a transferir
		cuentas[cuentaOrigen] -= cantidad;
		
		// indicamos que va a tener dos decimales
		System.out.printf("%10.2f de %d para %d ", cantidad, cuentaOrigen, cuentaDestino);
		
		// agrega al saldo de la cuenta destino la cantidad transferida
		cuentas[cuentaDestino] += cantidad;
		
		System.out.printf("Saldo total: %10.2f%n ", getSaldoTotal());
			
		
		/*
		 *  esto viene de la clase Object
		 *  notifica a los hilos de que ha terminado la ejecución
		 */
		notifyAll();
			// avisamos a los hilo dormidos
			// conditionSaldoSuficiente.signalAll();
//		} 
//		catch (Exception e) 
//		{
//			System.out.println(e);
//		}
//		finally 
//		{
//			// desbloqueamos el método -> recordar desbloquearlo
//			//lock.unlock();
//		}
	}
	
	public double getSaldoTotal()
	{
		double sumaCuentas = 0;
		
		for(double valor : cuentas)
		{
			sumaCuentas += valor;
		}
		return sumaCuentas;
	}
}
