package test.programacion.funcional.v7_interfaces_funcionales_standar;

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
        Integer  total = Flujo.proveer(10, () -> new Random().nextInt(10))
                .filtrar(valor -> valor % 2 == 0)
                .transformar((Integer valor) -> valor * valor)
                .actuar(System.out::println)
                .reducir(0, (valor1, valor2) -> valor1 + valor2);

        System.out.println("TOTAL:" + total);
    }
}
