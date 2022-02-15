package test.programacion.funcional.v11_streams;

// Forma funcional

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainLambdas
{
    public static void main(String[] args)
    {
        // esto para no hacer los metodos estaticos
        new MainLambdas();
    }

    public MainLambdas()
    {
        new Random().ints(10, 1, 11)
                .boxed() // convertimos en un Stream<Integer>
                .filter(valor -> valor <= 5)
                //.sorted(Comparator.naturalOrder()) // ordena de menor a mayor
                .sorted(Integer::compareTo) // ordena de menor a mayor
                .map(NumberUtils::elevarAlCuadrado)
                .map(Descripcion::new)
                .peek(System.out::println)
                .map(Descripcion::getValue)
                .forEach(System.out::println);
                //.reduce(1, Integer::sum);// .max(Integer::compareTo)
// .ifPresentOrElse(System.out::println,
//         () -> System.out.println("No existe valor"))
//.mapToInt(Integer::intValue)
//.sum();
                //System.out.println("Suma: " + suma)



    }

}
