package test.programacion.funcional.imperativav5.flujo;

// Forma imperativa



import test.programacion.funcional.imperativav5.flujo.interfaces.*;

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

        // Proveedor<Integer> define el tipo de flujo que quiero que devuelva
        Integer total = Flujo.proveer(10, new Proveedor<Integer>() {
                @Override
                public Integer obtener()
                {
                    return  new Random().nextInt(10);
                }
            })
            .filtrar(new Predicado<>() {
                @Override
                public boolean test(Integer valor) {
                    return valor % 2 == 0;
                }
            })
            .transformar(new OperadorUnario<>() {
                @Override
                public Integer aplicar(Integer valor) {
                    return valor * valor;
                }
            })
            .actuar(new Consumidor<>() {
                @Override
                public void aceptar(Integer valor) {
                    System.out.println(valor);
                }
            })
            .reducir(0, new OperadorBinario<Integer>() {
                @Override
                public Integer aplicar(Integer valor1, Integer valor2) {
                    return valor1 + valor2;
                }
            });

        System.out.println("TOTAL:" + total);


        /*
            Objetivo:
            1.- Crear lista de enteros
            2.- Qudarme solo con los pares
            3.- Pasar cada numero al cuadrado
            4.- Mostrar cada cuadrado por pantalla
            5.- Obtener la suma de los cuadrados
         */

        //1.- Crear lista de enteros
        /*Flujo<Integer> numeros = Flujo.proveer(10, new Proveedor<>()
        {
            @Override
            public Integer obtener()
            {
                return  new Random().nextInt(10);
            }
        });
        System.out.println(numeros);


        // 2.- Qudarme solo con los pares
        Flujo<Integer> pares = numeros.filtrar(new Predicado<>() {
            @Override
            public boolean test(Integer valor) {
                return valor % 2 == 0;
            }
        });
        System.out.println(pares);

        // 3-A.- Pasar cada numero al cuadrado
        Flujo<Integer> transformados =  pares.transformar(new OperadorUnario<>() {
            @Override
            public Integer aplicar(Integer valor) {
                return valor * valor;
            }
        });
        System.out.println(transformados);

        // 3-B.- Obtener cada numero convertido en cadena
        Flujo<String> convertidosCadena = transformados.transformar(new Funcion<Integer, String>() {
            @Override
            public String aplicar(Integer valor) {
                return "Valor: " + valor;
            }
        });
        System.out.println(convertidosCadena);

        // 4-A.- Mostrar cada cuadrado por pantalla y retornar lista
        Consumidor<Integer> impresor = new Consumidor<>() {
            @Override
            public void aceptar(Integer valor) {
                System.out.println(valor);
            }
        };


        Flujo<Integer> actuados = transformados.actuar(impresor);
        // 4-B.- Mostrar cada cuadrado por pantalla y no retornar nada
        transformados.consumir(impresor);

        // 5.- Obtener la suma de los cuadrados
        Integer sumaCuadrados = actuados.reducir(1, new OperadorBinario<Integer>() {
            @Override
            public Integer aplicar(Integer valor1, Integer valor2) {
                return valor1 + valor2;
            }
        });

        System.out.println("Suma: " + sumaCuadrados);*/

    }
}
