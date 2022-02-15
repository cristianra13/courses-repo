package com.app.test11_enums;

public enum KindsWeatherII 
{
	HOT("Warning HOT"),
	WARM("Just right!"),
	COLD("Warning COLD");
	
	private String caution;
	
	private KindsWeatherII(String caution)
	{
		this.caution = caution;
	}
	
	public String getCaution()
	{
		return this.caution;
	}
}
