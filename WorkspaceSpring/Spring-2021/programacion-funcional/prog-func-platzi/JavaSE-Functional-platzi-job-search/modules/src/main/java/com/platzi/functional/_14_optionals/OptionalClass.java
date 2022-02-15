package com.platzi.functional._14_optionals;

import java.util.*;

public class OptionalClass
{
    public static void main(String[] args)
    {
        List<String> names = getNames();

        Optional<List<String>> optionalNames  = getOptionalNames();
        if(optionalNames.isPresent()){
            // hacer algo
        }

        optionalNames.ifPresent(namesValue -> System.out.println(namesValue));

        Optional<String> valuablePlayer =  optionalValuablePlayer();
        valuablePlayer.orElse("Error: no player");
    }

    static List<String> getNames(){

        List<String> list = new ArrayList<>();

        return Collections.emptyList();
    }

    static String mostValue(){
        return null;
    }

    static Optional<List<String>> getOptionalNames()
    {
        List<String> list = new LinkedList<>();
        return Optional.of(list);
    }

    static Optional<String> optionalValuablePlayer()
    {
        /*
             le decimos a optional que tenemos un dato el cual no sabemos si es null
             con esto tenemos un objeto Optional y evitamos un nullPointerException
         */
        // return Optional.ofNullable();

        try
        {
            return Optional.of("Cristian");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // si no encuentra nada
        return Optional.empty();
    }
}
