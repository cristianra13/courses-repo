package com.app.test14_hashmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestHashMap {

	public static void main(String[] args) 
	{
		Product p1 = new Product("Cake");
		Product p2 = new Product("Bread");
		
		Map<Product, String> menu = new HashMap<>();
		menu.put(p1, "Del");
		menu.put(p2, "Sad");
		
		String res = menu.put(p1, "Deli");
		System.out.println(res);
		
		
		System.out.println();
		
		Set<Product> keys = menu.keySet();
		Collection<String> values = menu.values();
		
		keys.forEach(key -> System.out.println("Key: " + key.getName()));
		values.forEach(value -> System.out.println("Value: " + value));
	}

}
