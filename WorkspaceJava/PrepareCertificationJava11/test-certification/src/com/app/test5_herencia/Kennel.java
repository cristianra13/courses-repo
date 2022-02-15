package com.app.test5_herencia;

public class Kennel 
{
	public static void main(String... a)
	{
		Beaggle b1 = new Beaggle();
		Dog dog1 = new Dog();
		Dog d1 = b1;
		b1 = (Beaggle) d1;
	}
}
