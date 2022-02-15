package test.programacion.funcional.v9_method_references_avanzado;

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
                .ordenar(Integer::compare)
                .transformar(NumberUtils::elevarAlCuadrado)
                .transformar(Descripcion::new)
                .actuar(System.out::println)
                .transformar(Descripcion::getValue)
                .reducir(0, Integer::sum);

        System.out.println("TOTAL:" + total);
    }

    private int randomInt()
    {
        return new Random().nextInt(10);
    }
}
