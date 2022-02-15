package com.ordenamiento;

public class OrdenamientoSeleccion {

	public static void main(String[] args) 
	{
		// vector a ordenar
		int vector[] = {9,2,5,7,1,2,0};
		for(int i = 0; i < vector.length;i++)
			System.out.print(vector[i]);
		
		// variable auxiliar
		int temp;
		
		// primer cilco de recorrido
		for(int k = 0; k < vector.length-1; k++)
		{
			// almacenamiento de variable de recorrido
			int p = k;
			
			// segundo ciclo de recorrido
			for (int i = k+1; i <= vector.length-1; i++) 
			{
				// comparacion de valores de almacenaje de porciones
				if(vector[i] < vector[p])
					p = i;
				
				// comparacion de posiciones
				if(p != k)
				{
					// intercambio de valores en posiciones
					temp = vector[p];
					vector[p] = vector[k];
					vector[k] = temp;
				}
				
			}
		}
		System.out.println();
		for(int i = 0; i < vector.length;i++)
			System.out.print(vector[i]);
	}

}
