package com.app.jobsearch.cli;

import java.util.HashMap;
import java.util.Map;

public class CLIFunctions
{

    public static Map<String, Object> toMap(CLIArguments cliArguments)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("desscription", cliArguments.getKeyWord());
        params.put("location", cliArguments.getLocation());
        params.put("full_time", cliArguments.isFullTime());
        params.put("page", cliArguments.getPage());

        if(cliArguments.isMarkdown()){
            params.put("markdown", cliArguments.isMarkdown());
        }
        return params;
    }
}
