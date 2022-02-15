package com.app.test21_variables_casteos;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		new Test();
		
	}
	
	public Test() {
		main2();
		int i = 6;
		go(6);
	}

	static void go(Integer  x) {
		System.out.println("int");
	}
	
	static void go(long  x) {
		System.out.println("long");
	}
	
	private static boolean heatWave = true;
   public void main2() {
      boolean heatWave = false;
      System.out.println(heatWave);
   }
}

