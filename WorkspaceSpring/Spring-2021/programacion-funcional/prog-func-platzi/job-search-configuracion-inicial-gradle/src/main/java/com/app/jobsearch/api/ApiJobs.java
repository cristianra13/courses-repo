package com.app.jobsearch.api;

import com.app.jobsearch.model.JobPosition;
import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;
import java.util.Objects;

// cabeceras a enviar en la petición
@Headers("Accept: application/json")
public interface ApiJobs
{
    @RequestLine("GET /positions.json")
    List<JobPosition> jobs(@QueryMap Map<String, Object> queryMap);
}
