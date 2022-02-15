package com.ordenamiento;

public class OrdenamientoInsercion {

	public static void main(String[] args) 
	{
		// vector a ordenar
		int vector[] = {9,2,5,7,1,2,0};
		
		for(int i = 0; i < vector.length;i++)
			System.out.print(vector[i]);
	
		// primer ciclo de recorrido
		for(int i = 0; i < vector.length; i++)
		{
			// declaracion y asignacion de la variable auxiliar
			int aux = vector[i];
			
			// Declaracion de la variable del ciclo
			int j;
			
			// Segundo ciclo de recorrido
			for(j = i-1; j>= 0 && vector[j] > aux; j--)
			{
				//	Intercambio de valores por posición
				vector[j+1] = vector[j];						
			}
			// asignacion de variables al ciclo
			vector[j+1] = aux;
		}
		System.out.println();
		for(int i = 0; i < vector.length;i++)
			System.out.print(vector[i]);

	}

}
