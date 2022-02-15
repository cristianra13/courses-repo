package com.app.test12_arrays_and_loops;

import java.util.Arrays;
import java.util.Comparator;

public class Compare implements Comparator<String>
{

	@Override
	public int compare(String o1, String o2) 
	{
		return o2.length() - o1.length();
	}
	
	public static void main(String[] a)
	{
		String[] names = {"Mary","Jane","Elizabeth","Jo"};
		Arrays.sort(names, new Compare());
		for (String name: names) 
		{
		  System.out.println(name);
		}
	}
	

}
