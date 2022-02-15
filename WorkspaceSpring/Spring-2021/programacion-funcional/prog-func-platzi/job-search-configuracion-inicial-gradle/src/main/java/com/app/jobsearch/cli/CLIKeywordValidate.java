package com.app.jobsearch.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class CLIKeywordValidate implements IParameterValidator
{
    @Override
    public void validate(String name, String value) throws ParameterException
    {
        // valida que solo empiece con letras y seán letras y números
        if(!value.matches("[a-zA-Z]+[0-9]*$"))
        {
            System.err.println("El criterio de busqueda es invalido");
            throw new ParameterException("Unicam,ente letras y números");
        }
    }
}
