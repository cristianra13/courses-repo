package test.programacion.funcional.imperativav4.superfuncionesgenericasv4;

// Forma imperativa



import test.programacion.funcional.imperativav4.superfuncionesgenericasv4.interfaces.OperadorBinario;
import test.programacion.funcional.imperativav4.superfuncionesgenericasv4.interfaces.OperadorUnario;
import test.programacion.funcional.imperativav4.superfuncionesgenericasv4.interfaces.*;

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
        List<Integer> numeros = Superfunciones.proveer(10, new Proveedor<>() {
            @Override
            public Integer obtener() {
                return  new Random().nextInt(10);
            }
        });
        System.out.println(numeros);

        // 2.- Qudarme solo con los pares
        List<Integer> pares = Superfunciones.filtrar(numeros, new Predicado<>() {
            @Override
            public boolean test(Integer valor) {
                return valor % 2 == 0;
            }
        });
        System.out.println(pares);

        // 3-A.- Pasar cada numero al cuadrado
        List<Integer> transformados =  Superfunciones.transformar(pares, new OperadorUnario<>() {
            @Override
            public Integer aplicar(Integer valor) {
                return valor * valor;
            }
        });
        System.out.println(transformados);

        // 3-B.- Obtener cada numero convertido en cadena
        List<String> convertidosCadena = Superfunciones.transformar(pares, new Funcion<Integer, String>() {
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


        List<Integer> actuados = Superfunciones.actuar(transformados, impresor);
        // 4-B.- Mostrar cada cuadrado por pantalla y no retornar nada
        Superfunciones.consumir(transformados, impresor);

        // 5.- Obtener la suma de los cuadrados
        Integer sumaCuadrados = Superfunciones.reducir(actuados, 1, new OperadorBinario<Integer>() {
            @Override
            public Integer aplicar(Integer valor1, Integer valor2) {
                return valor1 + valor2;
            }
        });

        System.out.println("Suma: " + sumaCuadrados);

    }

}
