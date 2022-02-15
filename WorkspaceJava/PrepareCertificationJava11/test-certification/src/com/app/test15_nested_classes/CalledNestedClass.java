package com.app.test15_nested_classes;

public class CalledNestedClass 
{
	public static void main(String[] args) 
	{
		Order.createShippingMode("Fast");
		Order.createShippingMode("Normal");
		
		Order order1 = new Order();
		Order order2 = new Order();
	}
}
