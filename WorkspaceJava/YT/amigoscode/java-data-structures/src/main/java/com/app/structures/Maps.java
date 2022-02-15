package com.app.structures;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Maps {

  public static void main(String ...args) {
    Map<Person, Diamond> map = new HashMap<>();
    map.put(new Person("Jamilia"), new Diamond("African Diamond"));

    System.out.println(new Person("Jamilia").hashCode());
    System.out.println(new Person("Jamilia").hashCode());
    System.out.println(map.get(new Person("Jamilia")));
  }

  public static void maps() {
    Map<Integer, Person> map = new HashMap<>();
    map.put(1, new Person("Cristian", 28));
    map.put(2, new Person("Ali", 32));
    map.put(3, new Person("Mariam", 32));
    map.put(3, new Person("Mariam", 32));

    System.out.println(map);
    System.out.println(map.size());
    System.out.println(map.get(1));
    System.out.println(map.containsKey(1));
    System.out.println(map.entrySet());
    System.out.println(map.keySet());

    map.entrySet().forEach(System.out::println);
    map.forEach(((key, person) -> {
      System.out.println(key + " - " + person);
    }));

    map.remove(3);
    System.out.println(map.size());
    System.out.println(map.getOrDefault(4, new Person("Person Default", 0)));
    System.out.println(map.values());
  }

  static class Person {
    private String name;
    private int age;

    Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    Person(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Person person = (Person) o;
      return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, age);
    }

    @Override
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }

  static class Diamond {
    private String name;
    private int age;

    Diamond(String name, int age) {
      this.name = name;
      this.age = age;
    }

    Diamond(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Person person = (Person) o;
      return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, age);
    }

    @Override
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }
}
