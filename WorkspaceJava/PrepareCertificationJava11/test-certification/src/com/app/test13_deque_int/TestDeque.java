package com.app.test13_deque_int;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class TestDeque 
{
	public static void main(String... a)
	{
		new TestDeque();
	}
	
	public TestDeque()
	{
		Deque<String> deque = new ArrayDeque<>();
		deque.add("Cake");
		deque.add("Chocolate");
		deque.add("Cookie");
		deque.add("Tea");
		
//		String firstProduct = deque.pollFirst();
//		System.out.println(firstProduct);
//		System.out.println(deque);
//		
//		String secondProduct = deque.pollFirst();
//		System.out.println(firstProduct);
//		System.out.println(deque);
//		
//		boolean offF = deque.offerFirst("Cookie with chips");
//		System.out.println(offF);
//		System.out.println(deque);
//		
//		deque.offerLast("Bread");
//		System.out.println(deque);
//		
//		String lastProd = deque.pollLast();
//		System.out.println(lastProd);
//		System.out.println(deque);
		
		System.out.println();
		
		String el1 =  deque.peek();
		System.out.println(el1);
		String el2 = deque.peekFirst();
		System.out.println(el2);
		String el3 = deque.peekLast();
		System.out.println(el3);
		System.out.println(deque);
		
		
		System.out.println();
		
		String[] arr = {"Tea", "Cake"};
		List<String> texts = Arrays.asList(arr);
		
		System.out.println(texts);
		texts.set(0, "Coffee");
		// texts.add("Ice cream"); // esto da error ya que la lista es inmutable
		System.out.println(texts);
		
	}
}

