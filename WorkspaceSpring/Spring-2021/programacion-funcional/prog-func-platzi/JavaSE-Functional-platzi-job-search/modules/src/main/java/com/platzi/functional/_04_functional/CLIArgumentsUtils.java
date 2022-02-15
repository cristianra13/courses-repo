package com.platzi.functional._04_functional;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CLIArgumentsUtils {

    static void showHelp(CLIArguments cliArguments){
        Consumer<CLIArguments> consumerHelper = cliArg -> {
            if(cliArg.isHelp()){
                System.out.println("Manual ha sido soicitado");
            }
        };

        consumerHelper.accept(cliArguments);
    }

    static CLIArguments generateCLI(){
        // Supplier recibe un T y no retorna nada
        Supplier<CLIArguments> generator = () -> new CLIArguments();
        return generator.get();
    }
}
