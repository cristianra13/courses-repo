package com.app.test5_herencia;

public class SubClassInheritance1 extends ClassInheritance1 
{
	void print(String print) {
		System.out.println("Sub " +print);
	}
	
	public static void main(String ... a)
	{
		new SubClassInheritance1().print("text to print");
	}
}
