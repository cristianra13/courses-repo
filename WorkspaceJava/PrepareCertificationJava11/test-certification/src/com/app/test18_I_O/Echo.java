package com.app.test18_I_O;

import java.io.Console;
import java.io.PrintWriter;

public class Echo 
{
	public static void main(String[] args) 
	{
		Console c = System.console();
		if(c == null)
		{
			System.out.println("Console is not supported");
		}
		
		PrintWriter out  = c.writer();
		out.println("To quite type: exit");
		out.println("Type value and pres enter");
		String txt = null;
		
		while(!(txt = c.readLine()).equals("exit"))
		{
			out.println("Echo: " + txt);
		}
		
	}
}
