package com.example.controller;

import com.example.entities.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController
{
    private static final List<Student> students = Arrays.asList(
            new Student(1, "Cristian"),
            new Student(2, "Maria"),
            new Student(3, "Anna")
    );
    /*
   Se preautorizan los recursos dependiento del rol o el permiso
   Esto es lo mismo que se encuentra en ApplicationSecurityConfig
     */

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents()
    {
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student)
    {
        System.out.println(student);
    }

    @DeleteMapping(path = "{idStudent}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void deleteStudent(@PathVariable("idStudent") int idStudent)
    {
        System.out.println(idStudent);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudent(@RequestBody Student student)
    {

    }

}
