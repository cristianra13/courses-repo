package com.intraway.test.fizzbuzz.domain.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fizzbuzz_response")
public class ResponseFizzBuzz {

    @Id
    private String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy HH:mm:ss",
            timezone = "America/Bogota")
    private String min;
    private String max;
    private long timestamp;
    private int statusCode;
    private String description;
    private String uuid;
    private String list;

    public ResponseFizzBuzz(String min, String max, int statusCode, String description, String uuid, String list) {
        this.min = min;
        this.max = max;
        this.timestamp = new Date().getTime();
        this.statusCode = statusCode;
        this.description = description;
        this.uuid = uuid;
        this.list = list;
    }
}
