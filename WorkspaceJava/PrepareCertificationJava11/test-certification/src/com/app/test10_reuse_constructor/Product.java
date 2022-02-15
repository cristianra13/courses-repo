package com.app.test10_reuse_constructor;

import java.math.BigDecimal;

public class Product 
{
	private String name;
	private BigDecimal price;
	
	
	public Product(String name, double price) 
	{
		/*
		 * With this, we're reusing the second constructor
		 * So, this(name); is equals to this.name = name; 
		 */
		this(name);
		this.price = BigDecimal.valueOf(price);
	}
	
	public Product(String name) {
		this.name = name;
		this.price = BigDecimal.ZERO;
	}
	
	
	
}
