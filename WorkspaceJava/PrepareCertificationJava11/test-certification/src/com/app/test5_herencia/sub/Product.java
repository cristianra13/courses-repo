package com.app.test5_herencia.sub;

import java.math.BigDecimal;

public class Product 
{
	private double price = 38;
	
	public void printNumber(String name)
	{
		System.out.println(name + " " + BigDecimal.valueOf(price));
	}
	
	public void validations() {
		Food f = new Food();
		Food f2 = (Food) new Product();
		Product p = new Food();
	}
}
