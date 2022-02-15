package test.programacion.funcional.v2_superfunciones_clases;

// Forma imperativa

import test.programacion.funcional.imperativav2.superfunciones.clases.*;
import test.programacion.funcional.v2_superfunciones_clases.clases.*;

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
        List<Integer> numeros = Superfunciones.proveer(10, new NumNaturales());
        System.out.println(numeros);

        // 2.- Qudarme solo con los pares
        List<Integer> pares = Superfunciones.filtrar(numeros, new SoloImpares());
        System.out.println(pares);

        // 3.- Pasar cada numero al cuadrado
        List<Integer> transformados =  Superfunciones.transformar(pares, new AlCuadrado());
        System.out.println(transformados);

        // 4-A.- Mostrar cada cuadrado por pantalla y retornar lista
        List<Integer> actuados = Superfunciones.actuar(transformados, new Impresor());
        // 4-B.- Mostrar cada cuadrado por pantalla y no retornar nada
        Superfunciones.consumir(transformados, new Impresor());

        // 5.- Obtener la suma de los cuadrados
        Integer sumaCuadrados = Superfunciones.reducir(actuados, 1, new Multiplicador());

        System.out.println("Suma: " + sumaCuadrados);

    }

}
