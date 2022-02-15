package com.app.jobsearch.model.function;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CommanderFunctions
{
    public static <T> JCommander buildCommanderWithName(String cliName, Supplier<T> argSupplier)
    {
        JCommander jCommander = JCommander.newBuilder()
                .addObject(argSupplier.get())
                .build();
        jCommander.setProgramName(cliName);

        return jCommander;
    }

    public static Optional<List<Object>> parseArguments(JCommander jCommander, String[] args, Consumer<JCommander> onError)
    {
        try
        {
            jCommander.parse(args);
            return Optional.of(jCommander.getObjects());
        }
        catch(ParameterException ex)
        {
            onError.accept(jCommander);
        }

        return Optional.empty();
    }
}
