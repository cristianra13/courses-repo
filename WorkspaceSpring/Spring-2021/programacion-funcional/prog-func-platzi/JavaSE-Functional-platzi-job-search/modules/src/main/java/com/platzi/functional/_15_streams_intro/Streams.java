package com.platzi.functional._15_streams_intro;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Streams
{
    public static void main(String[] args)
    {
        List<String> courseList = NombresUtils.getList("Java", "Javascript", "Python", "Angular");
        //courseList.forEach(System.out::println);

        // creación de Stream
        Stream<String> courseStream = Stream.of("Java", "Javascript", "Python", "Angular");

        // convertir de un Stream a otro
        // en este caso courseStream se consume en está linea y ya se vuelve inutil
        Stream<String> newCoursesStream = courseStream.map(course -> course + "++");

        // Stream<Integer> coursesLengthStream = newCoursesStream.map(course -> course.length());
        // Optional<Integer> max = coursesLengthStream.max((x, y) -> y - x);

        Stream<String> justJavaCourses = newCoursesStream.filter(course -> course.contains("Java"));
        // justJavaCourses.forEach(System.out::println);

        Stream<String> enfasisCourses = justJavaCourses.map(course -> course + "!");
        // enfasisCourses.forEach(System.out::println);


        // Toda colección que implemente la interfaz Stream, puede generar un Stream de sus elementos
        Stream<String> coursesStream2 = courseList.stream();

        agregarOperadores(coursesStream2.map(course -> course + "!!")
                .filter(course -> course.contains("Java")))
                .forEach(System.out::println);
    }

    static <T> Stream<T> agregarOperadores(Stream<T> stream){
        return stream.peek(System.out::println);
    }
}
