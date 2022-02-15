package com.intraway.test.fizzbuzz.domain.models;

import lombok.*;
import java.util.Date;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy HH:mm:ss",
            timezone = "America/Bogota")
    private Date timestamp;
    private int httpStatusCode; // 200, 201, 400, 500....
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
        this.timestamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
}
