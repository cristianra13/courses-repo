package com.example.controller;

import com.example.entities.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "Cristian"),
            new Student(2, "Maria"),
            new Student(3, "Anna")
    );

    @GetMapping(path = "/{idStudent}")
    public Student getStudent(@PathVariable("idStudent") int idStudent){
        return students.stream().filter(student -> student.getIdStudent() == idStudent)
                .findFirst().orElseThrow(() -> new IllegalStateException("Studen " + idStudent+ " does not exist"));
    }
}
