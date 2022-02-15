package com.app.functional;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Book> myBooks = Arrays.asList(
        new Book("345-898", "El sufrimiento del hada", 2018, Genre.TERROR),
        new Book("345-900", "Halcon", 1990, Genre.THRILLER),
        new Book("346-700", "En la nada", 1815, Genre.TERROR),
        new Book("346-448", "Bababoda", 2010, Genre.COMEDY),
        new Book("586-393", "Zombieland", 2020, Genre.THRILLER),
        new Book("346-700", "En la nada", 1815, Genre.TERROR),
        new Book("586-395", "Zombieland 2", 2020, Genre.THRILLER));

    List<Book> books = myBooks.stream()
        .filter(book -> book.getYear() < 2000)
        .collect(Collectors.toList());
    //System.out.println(books);

    books.add(new Book("586-394", "Artico", 1999, Genre.TERROR));

    //System.out.println(books);

    TreeSet<Book> collect = myBooks.stream()
        .filter(book -> book.getYear() > 2000)
        .collect(Collectors.toCollection(TreeSet::new));

    // System.out.println(collect);
  }

}
