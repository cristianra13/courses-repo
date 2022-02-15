package com.app.test17_logging_api;

import java.util.logging.*;

public class TestLogging 
{

	private static Logger logger = Logger.getLogger(TestLogging.class.getName());
	
	public static void main(String[] args) 
	{
		try 
		{
			int t = 5 / 0;
		} 
		catch (Exception e) 
		{
			logger.log(Level.SEVERE, e.toString());
		}
		logger.log(Level.INFO, "My message");
		logger.info("My message");
	}
}
