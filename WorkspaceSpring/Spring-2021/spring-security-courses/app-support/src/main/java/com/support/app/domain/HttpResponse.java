package com.support.app.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpResponse
{
    private int httpStatusCode; // 200, 201, 400, 500
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public HttpResponse() { }

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message)
    {
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
}
