package com.app.test6_matematicas_binarias;

public class MainOne {

	public static void main(String... args) 
	{
		int a = -73;
		int b = ~a;
		int c = -(a+1);
		System.out.println(c);
		
		System.out.println();
		
		byte d = 5;
		byte e = 3;
		byte f = (byte)(5 & 3);
		byte g = (byte)(5 | 3);
		byte h = (byte)(5 ^ 3);
		
		System.out.println(f);
		System.out.println(g);
		System.out.println(h);
		
	}

}
