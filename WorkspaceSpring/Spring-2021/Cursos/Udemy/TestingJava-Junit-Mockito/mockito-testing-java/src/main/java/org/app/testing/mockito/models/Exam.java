package org.app.testing.mockito.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Exam {
    private Long id;
    private String name;
    private List<String> questions;

    public Exam() { }

    public Exam(Long id, String name) {
        this.id = id;
        this.name = name;
        this.questions = new ArrayList<>();
    }
}
