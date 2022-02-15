package com.app.jobsearch;

import com.app.jobsearch.api.ApiFunctions;
import com.app.jobsearch.api.ApiJobs;
import com.app.jobsearch.cli.CLIArguments;
import com.app.jobsearch.cli.CLIFunctions;
import com.app.jobsearch.model.JobPosition;
import com.beust.jcommander.JCommander;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.app.jobsearch.model.function.CommanderFunctions.buildCommanderWithName;
import static com.app.jobsearch.model.function.CommanderFunctions.parseArguments;

public class JobSearch
{
    public static void main(String[] args)
    {
        /*
            Primer parametro es el nombre de la app
            segundo parametro es desde donde recibe los parametros
         */
        JCommander jCommander = buildCommanderWithName("job-search", CLIArguments::new);

        // tranformar de la terminal a objetos de java
        // JCommander::usage muestra la ayuda
        Stream<CLIArguments> streamOfCLI =
                parseArguments(jCommander, args, JCommander::usage)
                .orElse(Collections.EMPTY_LIST)
                .stream()
                .map( obj -> (CLIArguments) obj );

        // obtener los argumentos que no seán ayuda
        Optional<CLIArguments> cliArgumentsOptional =
                streamOfCLI.filter(cli -> !cli.isHelp())
                .filter(cli -> cli.getKeyWord() != null)
                .findFirst();

        // convertimos el optional de argumentos a algo que pueda procesarce en la url
        cliArgumentsOptional.map(CLIFunctions::toMap)
                .map(JobSearch::executeRequest)
                .orElse(Stream.empty())
                .forEach(System.out::println);
    }

    private static Stream<JobPosition> executeRequest(Map<String, Object> params)
    {
        ApiJobs api = ApiFunctions.buildApp(ApiJobs.class,"https://jobs.github.com");
        return Stream.of(params)
                .map(api::jobs)
                .flatMap(Collection::stream);
    }
}
