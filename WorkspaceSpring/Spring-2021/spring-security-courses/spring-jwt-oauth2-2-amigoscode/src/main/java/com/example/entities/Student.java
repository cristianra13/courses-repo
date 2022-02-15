package com.example.entities;

import lombok.Data;

@Data
public class Student
{
    private int idStudent;
    private String nameStudent;

    public Student(int idStudent, String nameStudent) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
    }

    public Student() {}
}
