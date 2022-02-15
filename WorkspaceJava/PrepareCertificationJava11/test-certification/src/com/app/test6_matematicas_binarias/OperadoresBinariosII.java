package com.app.test6_matematicas_binarias;

public class OperadoresBinariosII 
{
	public static void main(String... a)
	{
		int x = 1, y = 1, z =0;
		if(x == y | x < ++y)
		{
			z = x + y;
		}
		else
		{
			z = 1;
		}
		System.out.println(z);
	}
}
