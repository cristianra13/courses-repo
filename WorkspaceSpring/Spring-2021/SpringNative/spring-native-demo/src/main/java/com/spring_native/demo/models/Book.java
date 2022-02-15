package com.spring_native.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    public static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private LocalDate year;
}
