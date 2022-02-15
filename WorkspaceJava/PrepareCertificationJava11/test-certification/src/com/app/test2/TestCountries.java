package com.app.test2;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestCountries {
	public static void main(String...args) 
	{
		new TestCountries();
		
		Set<String> islandNations = Set.of("Australia", "Japan", "Taiwan", "Cyprus", "Cuba");
		islandNations = islandNations.stream()
		        .map(swap)
		        .map(n -> n.substring(0, 1))
		        .collect(Collectors.toSet());
		for(String s : islandNations){
		    System.out.print(s);
		}
	}
	
	public static Function<String,String> swap = s -> {
	    if(s.equals("Australia"))
	        return "New Zealand";
	    else
	        return s;
	};
}
