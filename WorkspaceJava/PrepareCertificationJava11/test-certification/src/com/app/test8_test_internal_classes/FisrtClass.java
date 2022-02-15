package com.app.test8_test_internal_classes;

public class FisrtClass 
{

	private int age = 23;
	private String color = "Blue";
	
	
	private class Peth 
	{
		public void print(String anyValue, int anyAge) {
			System.out.println(anyValue + " " + anyAge);
		}
	}
}
