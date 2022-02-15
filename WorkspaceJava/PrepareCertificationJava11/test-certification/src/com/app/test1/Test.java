package com.app.test1;

import java.nio.file.Path;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.Supplier;

public class Test {
	
	public Test() {
	}

	public static void main(String[] args) {

		new Test();

		for (Planet p : Planet.values()) {
			System.out.println(p.getDistace());
		}
		// predicados devuelven un boolean
		// BiPredicate<Integer, Integer> add = (x, y) -> x + y;

		// las funciones reciben la entrada y la respuesta, es decir, T, R
		// Function<String> hello = () -> "hello";

		// los Supplier no devuelven nada - void
		DoubleConsumer cube = x -> System.out.println(x);
		

		// Consumer<T>

		String city = "munich";
		BiFunction<Integer, Integer, String> lambda = city::substring;
		System.out.println(city.charAt(0));
		

		// no puedo declarar una sola variable del lambda como String y la otra no
		// BiConsumer<String, String> lambda2 = (String x, y) ->
		// System.out.println(x+y);

		// en este caso da error porque debo retornar según me pide Function
//		Function<Double, Double> lambda3 = r -> {
//			double circ = 2*Math.PI*r;
//		};
		
		
		
		// el resultado despues de la 4 iteración es 11
		int x = 0;
		while(x < 100){
		     if(x%2 == 1){
		          x++;
		     }
		     else if(x%2 == 0){
		          x += 3;
		     }
		}
		
		
		
		Path someFile = Path.of("/","users","joe","docs","some.txt");
		   Path justSomeFile = someFile.getFileName();
		   Path docsFolder = someFile.getParent();
		   Path currentFolder = docsFolder.relativize(someFile);
		   System.out.println(currentFolder);
	}

	// esto retorna un Optional
//	public String pickName(){
//	    List<String> names = List.of("Barclay", "Barry", "Bert", "Bort");
//	    /*line 1*/
//	    return names.stream()
//	        .filter(n -> n.contains("Bart"))
//	        .findAny()
//	        /*line 2*/;
//	}

}
