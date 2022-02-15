package com.app.test5_herencia.sub;

import java.time.LocalDate;

public abstract class AbsClass 
{
	protected int age;
	protected String name;
	Object i;
	
	
	public LocalDate calculateDaysFromAge(LocalDate age)
	{
		return null;
	}
	
	abstract String calcOne();
	
	abstract int calcTwo();
	
	abstract boolean Three();

}
