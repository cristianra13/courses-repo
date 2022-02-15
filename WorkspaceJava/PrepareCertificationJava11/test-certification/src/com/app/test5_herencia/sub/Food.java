package com.app.test5_herencia.sub;

public class Food extends Product 
{
	@Override
	public void printNumber(String name) 
	{
		super.printNumber(name);
		System.out.println(name);
		validations(new Product());
		validations();
	}
	
	public void printNumber(Object name) 
	{
		System.out.println("\"Obj\" " +name);
	}
	
	public static void main(String a [])
	{
		new Food();
		Product p = new Food();
		p.printNumber("Tea");
	}
	
	public Food() {
		printNumber("Bread");
	}
	
	public void validations(Product p) {
		if(p instanceof Food) {
			System.out.println("Instancia de Food");
		}
		if(p instanceof Object) {
			System.out.println("Instancia de Object");
		}
		if(p instanceof Product) {
			System.out.println("Instancia de Product");
		}
		
		Food f = new Food();
		Food f2 = (Food) new Product();
		Product p2 = new Food();
		f2.validations();
	}
	
	public void validations() {
		
	}
	
}
