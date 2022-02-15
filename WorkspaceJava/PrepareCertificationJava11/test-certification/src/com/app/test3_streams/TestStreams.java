package com.app.test3_streams;

import java.util.List;

public class TestStreams {

	public static void main(String[] args) 
	{
		List<String> cities = List.of("Berlin", "Hamburg", "Cologne", "Frankfurt");
		cities.stream()
		// en este caso solo filtra por 
			.filter(city -> city.contains("e"))
			.filter(city -> city.contains("r"))
			.forEach(System.out::println);
		
		System.out.println();
		
		List<Integer> numbers = List.of(10, 20 ,30);
		numbers.stream()
			.map(number -> number + 10)
			.peek(number -> System.out.print(number))
			.map(number -> number + 10)
			.forEach(System.out::print);

	}

}
