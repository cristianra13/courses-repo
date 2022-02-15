package com.app.test6_matematicas_binarias;

public class OperadoresBinarios 
{
	public static void main(String... a) 
	{
		System.out.println("Operadores a nivel de bit");
		
		System.out.println("\nOperadores & | ^ :-+");
		
		int x = 10;	// 00001010
		int y = 9;	// 00001001
		int z = 8;	// 00001000
		
		int xAndY = x & y;
		int xOrY = x | y;
		int xXorz = x ^ z;
		
		System.out.println("& : "+ xAndY);	// = 8
		System.out.println("| : "+ xOrY);	// = 11
		System.out.println("^ : "+ xXorz);	// = 2
		
		
		System.out.println("\nOperadores binarios de complemento");
		/*
		 * 	La formula de esto es:
		 * 
		 *  ~x = (-x)-1
		 *  negación de x es igual a (-x) -1
		 *  
		 *  -(x+1)
		 */
		int numeroUno = 3;
		int nonUno = ~numeroUno;
		
		System.out.println("~numeroUno: " + nonUno);
		
		int numeroNegativo = -44;
		int nonNumeroNegativo = ~numeroNegativo;
		System.out.println("~nonNumeroNegativo: " + nonNumeroNegativo);
		
		System.out.println();
		
		System.out.println("\nOperadores Desplazamiento izquierda");
		int numeroDos = 2;
		int numeroA = numeroDos << numeroDos;
		System.out.println("numeroA: " + numeroA);	// = 8
		
		System.out.println("\nOperadores Desplazamiento derecha");
		int numeroDiez = 10;
		int numeroB = numeroDiez >> numeroDos;
		System.out.println("numeroB: " + numeroB);	// = 2
		
		int numeroNegativoDos = -2;
		System.out.println("\nNumero Original Negativo: " + numeroNegativoDos + " en binario " + Integer.toBinaryString(numeroNegativoDos));
		
		int numeroC = numeroNegativoDos >> numeroDos;
		System.out.println("numeroC: " + numeroC + " en binario " + Integer.toBinaryString(numeroC));
		
		
		System.out.println("\nOperadores Desplazamiento izquierda sin signo");
		int numeroD = numeroNegativoDos >>> numeroDos;
		System.out.println("numeroD: " + numeroD);
		
		
		System.out.println("\nOperadores Desplazamiento derecha sin signo");
		int numeroE = 12 >>> 1;
		System.out.println("nuemroE: " + numeroE + " en binario " + Integer.toBinaryString(numeroE));
		
	}
}
