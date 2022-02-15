package com.app.test12_arrays_and_loops;

import java.util.Iterator;

public class ArraysAndLoops 
{
	public static void main(String... a)
	{
		new ArraysAndLoops();
		
		loops();
		
//		for (int i = 0; i < 4; i++) {
//			if(i == 2) {
//				System.out.println("Ingreso");
//				continue;
//			}
//			if(i == 3) {
//				break;
//			}
//			System.out.println("No ingreso");
//				
//		}
//		
//		System.out.println("\nTerminar");
	}
	
	
	static void loops()
	{
//		int i = 0;
//		for (; i < 19; ) {	
//			i++;
//		}
		
		int sum = 0;
		for(int i = 0; i < 9; sum += i++);
		System.out.println(sum);
	
		System.out.println();
		
		int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
		for(int i = 0, j = 2; !(i == 3 || j == -1); i++, j--)
		{
			System.out.println(matrix[i][j]);
		}
		
		System.out.println();
		
		for(int i = 0, j = 0; i < matrix.length && j < matrix.length; i++, j++)
		{
			System.out.println(matrix[i][j]);
		}
		
		System.out.println();
		
		char[][] matrixChar = {
				{'A','B','C','D','E','F'},
				{'G','H','I','J','K','L'},
				{'M','N','O','P','Q','R'},
				{'S','T','U','V','W','Z'}
		};
		
		thisIsALoop:
		for(int i = 0; i < matrixChar.length; i ++) {
			for(int y = 0; y < matrixChar.length; y++) {
				if(matrixChar[i][y] == 'C') {
					continue;
				}
				if(matrixChar[i][y] == 'H') {
					continue thisIsALoop;
				}
				if(matrixChar[i][y] == 'N') {
					break;
				}
				if(matrixChar[i][y] == 'B') {
					break thisIsALoop; // este break termian con todas las iteraciones
				}
			}
			System.out.println("Next loop");
		}
		System.out.println("Finish loop");
	}
}
