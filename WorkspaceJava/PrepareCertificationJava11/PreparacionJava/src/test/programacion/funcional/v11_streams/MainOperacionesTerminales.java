package test.programacion.funcional.v11_streams;

import java.util.stream.Stream;

public class MainOperacionesTerminales
{

    public MainOperacionesTerminales()
    {
        /*
            Operaciones terminales de consulta - Stream
         */

        // busca si el resultado de toso los predicados es true
        boolean todosImpares = Stream.of(1, 2, 3)
                .allMatch(valor -> valor % 2 != 0);
         System.out.println(todosImpares);

        boolean algunImpar = Stream.of(1, 2, 3)
                .anyMatch(valor -> valor % 2 != 0);
        System.out.println(algunImpar);

        boolean ningunImpar = Stream.of(1, 2, 3)
                .noneMatch(valor -> valor % 2 != 0);
        System.out.println(ningunImpar);
    }

    public static void main(String[] args)
    {
        new MainOperacionesTerminales();
    }
}
