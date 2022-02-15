package test.programacion.funcional.imperativav3.superfunciones_inline_clases;

// Forma imperativa



import test.programacion.funcional.imperativav3.superfunciones_inline_clases.interfaces.*;

import java.util.List;
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
        /*
            Objetivo:
            1.- Crear lista de enteros
            2.- Qudarme solo con los pares
            3.- Pasar cada numero al cuadrado
            4.- Mostrar cada cuadrado por pantalla
            5.- Obtener la suma de los cuadrados
         */

        //1.- Crear lista de enteros
        List<Integer> numeros = Superfunciones.proveer(10, new Proveedor() {
            @Override
            public Integer obtener() {
                return new Random().nextInt(10);
            }
        });
        System.out.println(numeros);

        // 2.- Qudarme solo con los pares
        List<Integer> pares = Superfunciones.filtrar(numeros, new Predicado() {
            @Override
            public boolean test(Integer valor) {
                return valor % 2 == 0;
            }
        });
        System.out.println(pares);

        // 3.- Pasar cada numero al cuadrado
        List<Integer> transformados =  Superfunciones.transformar(pares, new Funcion() {
            @Override
            public Integer aplicar(Integer valor) {
                return valor * valor;
            }
        });
        System.out.println(transformados);

        // 4-A.- Mostrar cada cuadrado por pantalla y retornar lista
        Consumidor consumidor = new Consumidor() {
            @Override
            public void aceptar(Integer valor) {
                System.out.println(valor);
            }
        };
        
        List<Integer> actuados = Superfunciones.actuar(transformados, consumidor);
        // 4-B.- Mostrar cada cuadrado por pantalla y no retornar nada
        Superfunciones.consumir(transformados, consumidor);

        // 5.- Obtener la suma de los cuadrados
        Integer sumaCuadrados = Superfunciones.reducir(actuados, 1, new FuncionBinaria() {
            @Override
            public Integer aplicar(Integer valor1, Integer valor2) {
                return valor1 + valor2;
            }
        });

        System.out.println("Suma: " + sumaCuadrados);

    }

}
