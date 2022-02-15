package com.intraway;

public class Student {

  private String name;
  private Integer age;
  private Genre genre;

  public Student() {}

  public Student(String name, Integer age, Genre genre) {
    this.name = name;
    this.age = age;
    this.genre = genre;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", genre=" + genre +
        '}';
  }
}

enum Genre {
  FEMALE, MALE
}
