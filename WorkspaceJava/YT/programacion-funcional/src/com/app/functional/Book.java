package com.app.functional;

import java.util.Objects;

public class Book implements Comparable<Book>
{
    private String id;
    private String name;
    private int year;
    private Genre genre;

    public Book()
    {

    }

    public Book(String id, String name, int year, Genre genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && id.equals(book.id) && name.equals(book.name) && genre == book.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, genre);
    }

    @Override
    public int compareTo(Book o) {
        return name.compareTo(o.name);
    }
}
