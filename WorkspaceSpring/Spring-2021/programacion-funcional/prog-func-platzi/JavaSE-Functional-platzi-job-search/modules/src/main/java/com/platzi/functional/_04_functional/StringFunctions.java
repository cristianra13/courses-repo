package com.platzi.functional._04_functional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class StringFunctions {

    public static void main(String[] args) {
        UnaryOperator<String> quote = text -> "\"" + text + "\"";
        UnaryOperator<String> addMark = text -> text + "!";

        System.out.println(quote.apply("Hola estudiante!!!"));
        System.out.println(addMark.apply("Helo Cristian real"));

        BiFunction<Integer, Integer, Integer> multiplicacion = (x, y) -> x * y;
        System.out.println(multiplicacion.apply(2,8));

        // en este caso, se infiere que los demas argumentos de la función son del mismo tipo del cual se encuentra, Integer0
        BinaryOperator<Integer> suma = (x, y) -> x + y;
        System.out.println(suma.apply(7, 11));

        /*
            Agregaremos espacios a la izquierda| a un String.
            String al cual vamos a agregar los espacios
            Integer, la cantidad de espacios a agregar
         */
        BiFunction<String, Integer, String> leftPad =
                (texto, numeroEspacios) -> String.format("%"+numeroEspacios + "s",  texto);
        System.out.println(leftPad.apply("Hola", 10));

        //  List<BiFunction<String, Integer, String>> formateadores = (texto, numEspacios) -> String.format("%"+ numEspacios + "s",  texto);
    }
}
