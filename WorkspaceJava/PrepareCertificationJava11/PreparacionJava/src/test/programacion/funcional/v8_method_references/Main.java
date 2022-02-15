package test.programacion.funcional.v8_method_references;

// Forma funcional

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
        Integer  total = Flujo.proveer(10, this::randomInt)
                .filtrar(NumberUtils::esPrimo)
                .transformar(NumberUtils::elevarAlCuadrado)
                .actuar(System.out::println)
                .reducir(0, Integer::sum);

        System.out.println("TOTAL:" + total);
    }

    private int randomInt()
    {
        return new Random().nextInt(10);
    }
}
