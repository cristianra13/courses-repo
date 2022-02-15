package com.app.structures;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

/**
 * Queue use FIFO -> First In First Out
 */
public class Queues {

  public static void main(String[] args) {
    LinkedList<Person> linkedList = new LinkedList<>();
    linkedList.add(new Person("Cristian", 28));
    linkedList.add(new Person("Alexa", 28));
    linkedList.addFirst(new Person("Jean", 28));
    ListIterator<Person> personListIterator = linkedList.listIterator();
    while (personListIterator.hasNext()) {
      System.out.println(personListIterator.next());
    }

    System.out.println();
    while (personListIterator.hasPrevious()) {
      System.out.println(personListIterator.previous());
    }
  }

  private static void queues() {
    Queue<Person> supermarket = new LinkedList<>();
    supermarket.add(new Person("Cristian", 28));
    supermarket.add(new Person("Marian", 18));
    supermarket.add(new Person("Ali", 36));

    System.out.println(supermarket.size());
    System.out.println(supermarket.peek());
    // poll delete first element, this method not return exception if queue is empty
    System.out.println(supermarket.poll());
    System.out.println(supermarket.size());
    // peek show the first element, this not delete the element
    System.out.println(supermarket.peek());
    System.out.println(supermarket.size());
  }

  static class Person {
    private String name;
    private int age;

    Person(String name, int age) {
      this.name = name;
      this.age = age;
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
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }
}
