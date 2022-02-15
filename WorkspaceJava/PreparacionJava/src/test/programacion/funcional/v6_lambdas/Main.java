package test.programacion.funcional.v6_lambdas;

// Forma imperativa



import test.programacion.funcional.v6_lambdas.interfaces.*;

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
        Integer  total = Flujo.proveer(10, () -> new Random().nextInt(10))
                .filtrar(valor -> valor % 2 == 0)
                .transformar((Integer valor) -> valor * valor)
                .actuar(System.out::println)
                .reducir(0, (valor1, valor2) -> valor1 + valor2);

        System.out.println("TOTAL:" + total);
    }
}
