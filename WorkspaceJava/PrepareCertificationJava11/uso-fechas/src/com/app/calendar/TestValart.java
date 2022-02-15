package com.app.calendar;

import java.util.Iterator;

public class TestValart 
{
	public static void main(String[] args) 
	{
		String mystring1 = "HOLA";
		String mystring2 = "OLGA";
		String subSecuencia = "";
		char[] arrAr = mystring1.toCharArray();
		
		for(int i = 0; i < arrAr.length; i++) {
			
			for(int j = i; j < arrAr.length; j++) {
				String sub = String.valueOf(arrAr[i]);
				
				if(mystring2.contains(sub)) {
					if(sub.length() >= subSecuencia.length()) {
						subSecuencia += sub;
						break;
					}
				}
			}
		}
		System.out.println(subSecuencia);
	}

}
