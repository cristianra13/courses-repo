package com.app.test10_reuse_constructor;

public class ClassTestMethods
{
	public ClassTestMethods(){
		System.out.println("Print from constructor");
	}
	
	
	// Estos metodos se ejecutaran primero que el constructor
	{
		System.out.println("First enter");
	}
	
	// el estatico se ejecutara primero
	static {
		System.out.println("Second enter");
	}
	
	public static void main(String... a)
	{
		// si no se inicializa la clase, no se ejecutan los metodos externos
		new ClassTestMethods();
		System.out.println("Third enter");
		
		int i = 0;
		
		System.out.println(i++);
		System.out.println(++i);
		
	}
}
