package com.platzi.functional._04_functional;

import java.util.function.Function;
import java.util.function.Predicate;

public class MathFunctions {

    public static void main(String[] args) {

        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * x;
            }
        };

        System.out.println(function.apply(12));


        Function<Integer, Boolean> isOdd = x -> x % 2 == 0;
        isOdd.apply(6);

        /*
            Predicate trabaja sobre un tipo y genera un boolean
         */
        Predicate<Integer> isEven = x -> x % 2 != 0;
        isEven.test(9);


        Predicate<Student> isApproved = student -> student.getCalificacion() > 6;
        System.out.println("Approved:" + isApproved.test(new Student(8)));
    }

    static class Student{
        private double calificacion;

        public Student(double calidicacion){
            this.calificacion = calidicacion;
        }

        public double getCalificacion() {
            return calificacion;
        }
    }
}
