package com.intraway;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<Student> students = List.of(
        new Student("Cristian", 28, Genre.MALE),
        new Student("Juana", 32, Genre.FEMALE),
        new Student("Frank", 45, Genre.MALE),
        new Student("Katherine", 19, Genre.FEMALE),
        new Student("Lisa", 26, Genre.FEMALE)
    );

    // Imperativa
    for (Student student : students) {
      if (Genre.FEMALE.equals(student.getGenre())) {
        System.out.println(student);
      }
    }

    System.out.println("\n");

    // Declarativa
    students.forEach(System.out::println);
    System.out.println("\n");
    // Only FEMALE
    students.stream()
        .filter(student -> Genre.FEMALE.equals(student.getGenre()))
        .forEach(System.out::println);
  }

}
