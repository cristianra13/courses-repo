package com.app.test5_herencia;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String... a) {
		
		String t = "hello world!";
		String message = t.substring(6, 12) + t.substring(12, 6);
		System.out.println(message);
		
		System.out.println("Super");
		
		List lista = new ArrayList<>();
		lista.iterator();
	}
}


