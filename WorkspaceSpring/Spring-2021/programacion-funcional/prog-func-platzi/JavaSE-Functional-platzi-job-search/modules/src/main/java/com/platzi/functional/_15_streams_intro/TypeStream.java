package com.platzi.functional._15_streams_intro;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TypeStream
{
    public static void main(String[] args)
    {
        IntStream infiniteStream = IntStream.iterate(0, x -> x+1);

        List<Integer> collect = infiniteStream.limit(1000)
                .filter(x -> x % 2 == 0)
                /*
                    Cuando se ejecuta este metodo, Stream se encarga de distribuir todos los
                    procesos en todos los procesadores
                 */
                // .parallel()
                /*
                    allMatch es una función que trabaja sobre enteros

                    Revisa que todos sean pares
                 */
                // .allMatch(x -> x % 2 == 0);
                .boxed() // devuelve un Stream de Integer
                .collect(Collectors.toList());
    }
}
