package com.app.test15_nested_classes;

public class Order 
{
	public static void createShippingMode(String description)
	{
		new ShippingMode(description);
	}

	
	private static class ShippingMode
	{
		private String description;

		public ShippingMode(String description) 
		{
			this.setDescription(description);
		}

		public String getDescription() 
		{			
			return description;
		}

		public void setDescription(String description) 
		{
			this.description = description;
		}
	}
}
