package com.app.test9_variable_number_args;

public class Varargs 
{
	public static void main(String...a) {
		new Varargs();
	}
	
	
	public Varargs()
	{
		setFiscalDetails(1.99);
		setFiscalDetails(7.99, 0.33);
		setFiscalDetails(0.88, 5.21, 4);
	}
	
	public void setFiscalDetails(double...values){
		
		int len = values.length;
		switch (len) {
		case 1:
			System.out.println("This length is " + len);
			break;
		case 2:
			System.out.println("This length is " + len);
			break;
		case 3:
			System.out.println("This length is " + len);
			break;
	}
	}
}
