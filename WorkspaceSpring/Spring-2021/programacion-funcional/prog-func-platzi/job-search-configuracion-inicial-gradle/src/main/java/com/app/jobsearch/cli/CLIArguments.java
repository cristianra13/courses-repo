package com.app.jobsearch.cli;

import com.beust.jcommander.Parameter;

/*
    Usaremos solo getters ya que la clase será inmutable,
    JComander trabaja a traves de notaciones, est+as son para
    porder trabajar con el CLI
 */
public class CLIArguments
{

    // indicamos que este atributo es una propiedad que se puede recibir por terminal
    @Parameter(
            required = true,
            descriptionKey = "KEYWORD",
            validateWith = CLIKeywordValidate.class,
            description = "KEYWORD"
    )
    private String keyWord;

    @Parameter(
            names = {"--location", "-l"},
            description = "Cada busqueda puede incluir una ubicación"
    )
    private String location;

    @Parameter(
            names = {"--page", "-p"},
            description = "La API devuelve 50 resultados, usa un número para la pagina"
    )
    private int page;

    @Parameter(
            names = {"--full-time"},
            description = "Agregar si queremos trabajos de tiempo completo"
    )
    private boolean isFullTime;

    @Parameter(
            names = {"--markdown"},
            description = "Obtener los resultados en Markdown"
    )
    private boolean isMarkdown;

    @Parameter(
            names = "--help",
            help = true,
            validateWith = CLIHelpValidator.class,
            description = "Mostrar está ayuda"
    )
    private boolean isHelp;

    public CLIArguments(){ }

    public String getKeyWord() {
        return keyWord;
    }

    public String getLocation() {
        return location;
    }

    public int getPage() {
        return page;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public boolean isMarkdown() {
        return isMarkdown;
    }

    public boolean isHelp() {
        return isHelp;
    }

    @Override
    public String toString() {
        return "CLIArguments{" +
                "keyWord='" + keyWord + '\'' +
                ", location='" + location + '\'' +
                ", page=" + page +
                ", isFullTime=" + isFullTime +
                ", isMarkdown=" + isMarkdown +
                ", isHelp=" + isHelp +
                '}';
    }

    /*
        Funcion encargada de que si se necesita modificar la manera
        en que se crean los argumentos de terminal, cambien,
        de esa manera no necesitaresmos de constructor
     */
    public static CLIArguments newInstance()
    {
        return new CLIArguments();
    }
}
