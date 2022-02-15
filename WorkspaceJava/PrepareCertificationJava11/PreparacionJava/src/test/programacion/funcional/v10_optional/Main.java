package test.programacion.funcional.v10_optional;

// Forma funcional

import java.util.Optional;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        // esto para no hacer los metodos estaticos
        new Main();
    }

    public Main()
    {
        Flujo.proveer(10, this::randomInt)
                .filtrar(valor -> valor >= 0)
                .ordenar(Integer::compare)
                .transformar(NumberUtils::elevarAlCuadrado)
                .transformar(Descripcion::new)
                .actuar(System.out::println)
                .transformar(Descripcion::getValue)
                //.reducir(0, Integer::sum);
                .max(Integer::compareTo)
                .ifPresentOrElse(
                        valor -> System.out.println("Maximo "+valor.doubleValue()),
                        () -> System.out.println("No hay máximo porque el flujo está vacío")
                );
                ;

    }

    private int randomInt()
    {
        return new Random().nextInt(10);
    }
}
