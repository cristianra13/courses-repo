package com.platzi.functional._11_composition;

import java.util.function.Function;

public class MathOperationsII
{
    public static void main(String[] args)
    {
        Function<Integer, Integer> multiplyBy3 = x -> {
            System.out.println("Multiplicando 3 * " + x);
            return x * 3;
        };

        Function<Integer, Integer> addOneMultBy3 = multiplyBy3.compose(y -> {
            System.out.println("Le agregare 1 a " + y);
            return y + 1;
        });

        // System.out.println(addOneMultBy3.apply(5));

        Function<Integer, Integer> andSquare =
                addOneMultBy3.andThen(x -> {
                    System.out.println("Estoy elevando " + x + " al cuadrado");
                    return x * x;
                });

        System.out.println(andSquare.apply(3));

    }
}
