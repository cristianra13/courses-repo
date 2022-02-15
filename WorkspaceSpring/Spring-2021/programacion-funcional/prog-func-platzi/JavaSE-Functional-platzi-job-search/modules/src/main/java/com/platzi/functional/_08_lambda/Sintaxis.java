package com.platzi.functional._08_lambda;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Sintaxis
{
    public static void main(String[] args)
    {
        List<String> cursos = NombresUtils.getList("Java", "Funtional");

        cursos.forEach( curso -> {
            curso = curso.toUpperCase();
            System.out.println(curso);
        });


        BiFunction<Integer, Integer, String> s = (x,y) -> x + y + " Es el resultado";
        System.out.println(s.apply(3, 6));

        usarZero(() -> 3);

        usarPredicado(x ->  x.isEmpty());

        usarBiFunction( (x, y) -> x * y);

        usarBiFunction( (Integer x, Integer y) -> x * y);

        userNada(() -> {
            /*
                puedo hacer muchas cosas,
                como al tiempo no se puede hacer nada si se quiere
             */
            System.out.println("Solo imprimo algo");
        });

    }

    static void usarZero(ZeroArgs zeroArgs){
        zeroArgs.get();
    }

    static void usarPredicado(Predicate<String> predicate){

    }

    static void usarBiFunction(BiFunction<Integer, Integer, Integer> operaciones){

    }

    static void userNada(OperarNada operarNada){
        operarNada.hacerNada();
    }

    @FunctionalInterface
    interface ZeroArgs
    {
        int get();
    }


    @FunctionalInterface
    interface OperarNada {
        void hacerNada();
    }
}
