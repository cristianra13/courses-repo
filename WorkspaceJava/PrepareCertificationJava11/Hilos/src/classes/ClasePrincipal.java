package classes;

public class ClasePrincipal 
{
	
	public static void main(String[] args) 
	{
		
		/*
		 * Para poder usar hilos cuando se hereda de Thread, se debe crear
		 * una instancia de la clase que hereda de Thread,
		 * en este caso Proceso1
		 */
		Proceso1 hilo1 = new Proceso1();
		
		/*
		 * La otra manera es, cuando se implementa de Runnable,
		 * debemos crear un objeto de la clase Thread
		 */
		Thread hilo2 = new Thread(new Proceso2());
		
		
		
		/*
		 * Metodos que nos permiten arrancar los hilos
		 */
		hilo1.start();
		hilo2.start();
		
	
		
		
		
		
		
		
		
		
		
		/*
		 * El segundo proceso se ejecuta cuando acabe el proceso 1
		 */
//		for(int i = 0; i <= 5; i++)
//		{
//			System.out.println("Proceso 1");
//		}
//		
//		System.out.println();
//		
//		for(int i = 0; i <= 5; i++)
//		{
//			System.out.println("Proceso 2");
//		}
	}
}
