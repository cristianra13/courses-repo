package com.platzi.functional._05_sam;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public class AgeUtils
{
    public static void main(String[] args)
    {
        Function<Integer, String> addZeros = x -> x < 10 ? "0" + x : String.valueOf(x);

        TriFunction<Integer, Integer, Integer, LocalDate> parseDate =
                // (day, month, year) -> LocalDate.of(day, month, year);
                (day, month, year) -> LocalDate.parse(year + "-" + addZeros.apply(month) + "-" + addZeros.apply(day));

        TriFunction<Integer, Integer, Integer, Integer> calculateAge =
                (day, month, year) -> Period.between(parseDate.apply(day,month,year), LocalDate.now()).getYears();

        Integer age = calculateAge.apply(29, 6, 1993);
        System.out.println("Age: " + age);
        System.out.println(~age +1);
    }

    /*
        A partir de Java 8, podemos usar una notación para decir
        que está interfaz se usa como una función

        Está interface es propia, recibe tres parametros
     */
    @FunctionalInterface
    interface TriFunction<T, U, V, R>{
        R apply(T t, U u, V v);
    }
}
