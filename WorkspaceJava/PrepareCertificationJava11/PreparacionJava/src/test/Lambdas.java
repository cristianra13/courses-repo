package test;

import java.util.stream.IntStream;

public class Lambdas
{
    public void probar( String seleccion)
    {
        switch(seleccion)
        {
            case "PAR":
                IntStream.of(1, 2, 3, 4, 5, 6, 7).filter((var i) -> i % 2 == 0).forEach(System.out::println);
                break;
            case "IMPAR":
                IntStream.of(1, 2, 3, 4, 5, 6, 7).filter((var i) -> i % 2 != 0).forEach(System.out::println);
                break;
        }
    }

    public void sumar()
    {
        IOperacion iOperacion = (double x, double y) -> (x *= y);
        double resultado = iOperacion.sumar(2, 2.0);
        System.out.println(resultado);
    }
}
