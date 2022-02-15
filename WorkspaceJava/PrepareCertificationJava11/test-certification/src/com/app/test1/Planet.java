package com.app.test1;

public enum Planet {
	MERCURY(0.39), 
	VENUS(), 
	EARTH;

	private double distanceFromSunAU = 1.0;

	private Planet(double distance) {
		distanceFromSunAU = distance;
	}

	private Planet() {
		distanceFromSunAU = 5.0;
	}

	public double getDistace() {
		return distanceFromSunAU;
	}
}

