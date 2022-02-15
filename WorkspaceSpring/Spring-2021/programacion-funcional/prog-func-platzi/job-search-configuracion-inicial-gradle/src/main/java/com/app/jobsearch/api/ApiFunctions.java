package com.app.jobsearch.api;

import feign.Feign;
import feign.gson.GsonDecoder;

public interface ApiFunctions
{
    static <T> T buildApp(Class<T> tClass, String url){
        return Feign.builder() // construimos cliente web
                .decoder(new GsonDecoder()) // decodificamos los resultados usando Gson
                .target(tClass, url); // apuntando a un api que está en una url
    }
}
