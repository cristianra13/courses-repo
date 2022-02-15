package com.platzi.functional._07_inference;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.function.Function;

public class Inferencia
{
    public static void main(String[] args)
    {
        Function<Integer, String> funcionConvertidora = x -> String.format("El resultado  de entero %s * 3 = ", x, x*3);
        System.out.println(funcionConvertidora.apply(3));


        List<String> alumnos = NombresUtils.getList("Hugo", "Paco", "Luis");
        alumnos.forEach(System.out::println);
    }

    static Function<Integer, Integer> suma = x -> x + x;
}
