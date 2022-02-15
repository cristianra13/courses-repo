package com.ordenamiento;

public class OrdenamientoBurbuja 
{
	public static void main(String[] args)
	{
		// vector a ordenar
		int vector[] = {9,2,5,7,1,2,0};
		for(int i = 0; i < vector.length;i++)
			System.out.print(vector[i]);
		
		// Variable auxiliar
		int temp;
		
		// primer ciclo de recorridos
		for(int i = 0; i < vector.length; i++)
		{
			// Segundo ciclo de recorridos
			for(int j = 0; j < vector.length-1; j++)
			{
				/*
				 * Comaracion de las posiciones y sus valores 
				 * para determinar el mayor de la comparacion
				 */
				if(vector[j] > vector[j+1])
				{
					temp = vector[j];
					vector[j] = vector[j+1];
					vector[j+1] = temp;
				}
			}
			
		}
		System.out.println();
		for(int i = 0; i < vector.length;i++)
			System.out.print(vector[i]);
	}

}
