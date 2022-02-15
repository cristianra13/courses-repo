package test.programacion.funcional.v1_imperativa;

// Forma imperativa

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> numeros = crearLista();
        System.out.println(numeros);

        // 2.- Qudarme solo con los pares
        List<Integer> pares = filtrarPares(numeros);
        System.out.println(pares);

        // 3.- Pasar cada numero al cuadrado
        List<Integer> numeroCuadrados =  calcularCuadrado(pares);
        System.out.println(numeroCuadrados);

        // 4.- Mostrar cada cuadrado por pantalla
        mostrarNumeros(numeroCuadrados);

        // 5.- Obtener la suma de los cuadrados
        int sumaCuadrados = sumaCuadrados(numeroCuadrados);

        System.out.println("Suma: " + sumaCuadrados);

    }

    private List<Integer> crearLista()
    {
        return List.of(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
    }
    
    private List<Integer> filtrarPares(List<Integer> numeros)
    {
        // 2.- Qudarme solo con los pares
        List<Integer> resultado = new ArrayList<>();

        for(var numero : numeros)
        {
            if(numero % 2 == 0)
            {
                resultado.add(numero);
            }
        }
        return resultado;
    }

    private List<Integer> calcularCuadrado(List<Integer> pares)
    {
        List<Integer> cuadrados = new ArrayList<>();
        for(var numero : pares)
        {
            cuadrados.add(numero * numero);
        }
        return cuadrados;
    }

    private void mostrarNumeros(List<Integer> numeros)
    {
        for (var numero : numeros)
        {
            System.out.println(numero);
        }
    }

    private int sumaCuadrados(List<Integer> cuadrados)
    {
        int resultado  = 0;
        for(var numero : cuadrados)
        {
            resultado += numero;
        }
        return resultado;
    }

}
