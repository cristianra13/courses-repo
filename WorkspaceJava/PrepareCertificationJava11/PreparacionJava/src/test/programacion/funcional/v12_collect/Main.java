package test.programacion.funcional.v12_collect;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static test.programacion.funcional.v12_collect.Genre.*;

public class Main
{
    public static void main(String[] args)
    {
        new Main();
    }

    public Main()
    {
        /*
            Reduciión MUTABLE
         */
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

        books.add(new Book("586-394", "Artico", 1999, Genre.TERROR));
        //System.out.println(books);



        /*
            Reducción INMUTABLE
         */
        List<Book> collectBooks = myBooks.stream()
                .filter(book -> book.getYear() < 2000)
                .collect(Collectors.toUnmodifiableList());

        // acá lanza una excepción ya que la lista es inmodificable según se agrego anteriormente
        //collectBooks.add(new Book("586-394", "Artico", 1999, Genre.TERROR));
        //System.out.println(collectBooks);

        /*
            Recolectar hacía un conjunto
         */

        /*
            set no permite lementos repetidos
            en este caso muestra los dos libros con los mismo datos,
            podemos solucionar esto sobreescribiendo en la clase Book el método equals
         */
        Set<Book> setBooks = myBooks.stream()
                .filter(book -> book.getYear() < 2000)
                .collect(Collectors.toSet());
        // System.out.println(setBooks);

        /*
            SET INMUTABLE
         */
        Set<Book> setBooksUnm = myBooks.stream()
                .filter(book -> book.getYear() < 2000)
                .collect(Collectors.toUnmodifiableSet());
        // acá se genera un error ya que no permite agregar un elemento a un Set inmodificable.
        //setBooksUnm.add(new Book("346-200", "SOn niños", 1815, Genre.COMEDY));


        TreeSet<Book> collect = myBooks.stream()
                .filter(book -> book.getYear() > 2000)
                .collect(Collectors.toCollection(TreeSet::new));

        // System.out.println(collect);


        /*
            Recolectar en MAPA toMap
         */

        /*
            Map recibe dos funciones,
            la primera le decimos que queremos como clave del mapa

             para la segunda le decimos que queremos que como valor se almacene como tal el propio libro,
             ver también que para la segunda función, lo mismo que yo recibo es lo mismo que yo retorno
             Function.identity(): metodo estatico predefinido al que podemos decirle que lo que recibimos es lo mismo que retornamos.
         */
        Map<String, Book> result =  myBooks.stream()
                .filter(book -> book.getYear() > 2000)
                //.collect(Collectors.toMap(Book::getId, book -> book));
                .collect(Collectors.toMap(Book::getId, Function.identity()));
        // System.out.println(result);



        Map<String, Book> resultT =  myBooks.stream()
                .filter(book -> book.getYear() < 2000)
                /*
                    combinamos los dis libros repetidos, es el que en el nombre está con +
                    ya que toma estos dos valores que existen, y no permite a traves de un BinaryOperator,
                    combinarlos y guardar solo uno
                 */
                .collect(Collectors.toMap(Book::getId,
                        Function.identity(),
                        (book1, book2) -> new Book(book1.getId(), book1.getName() + " +", book1.getYear(), book1.getGenre())));
        // System.out.println(resultT);




        // para este caso creamos un TreeMap y no un HashMap
        Map<String, Book> resultT2 =  myBooks.stream()
                .filter(book -> book.getYear() < 2000)
                /*
                    combinamos los dis libros repetidos, es el que en el nombre está con +
                    ya que toma estos dos valores que existen, y no permite a traves de un BinaryOperator,
                    combinarlos y guardar solo uno
                 */
                .collect(Collectors.toMap(Book::getId,
                        Function.identity(),
                        (book1, book2) -> new Book(book1.getId(), book1.getName() + " +", book1.getYear(), book1.getGenre()),
                        TreeMap::new
                ));




        /*
            Recolectar en ARRAY
         */

        /*
            Se retorna un array de objetos ya que los arrays no usan genericos
         */
        Object[] objects = myBooks.stream()
                .filter(book -> book.getYear() < 2000)
                .toArray();
        //Arrays.stream(objects).forEach(System.out::println);


        /*
        De está forma podemos retorna un array de book, Book[]
         */
        Book[] objectsTwo = myBooks.stream()
                .filter(book -> book.getYear() < 2000)
                // se crea un new Book del tamaño que se ha pasado a la lambda
                .toArray(Book[]::new);
        // Arrays.stream(objectsTwo).forEach(System.out::println);


        /*
            Recolectar hacía una cadena de caracteres
         */
        String stringResp = myBooks.stream()
                .distinct()
                .map(Book::getName)
                /*
                    Este collector solo funciona con CharSecuence u objetos que extiendan de este como Stream
                    - Dentro de joining() podemos agregar un delimitador para cada cadena
                 */
                .collect(Collectors.joining(","));
        //System.out.println(stringResp);


        String stringRes = myBooks.stream()
                .distinct()
                .filter(book -> book.getYear() > 2021)
                .map(Book::getName)
                /*
                    Este collector solo funciona con CharSecuence u objetos que extiendan de este como Stream
                    - Dentro de joining() podemos agregar un delimitador para cada cadena
                    - En este caso podemos pasar un prefijo y un sufijo
                 */
                .collect(Collectors.joining(",", "(", ")"));
        //System.out.println(stringRes);

        String stringRes2 = myBooks.stream()
                .distinct()
                .filter(book -> book.getYear() < 2001)
                 /*
                    hacemos en una sola linea el map y el Collectors
                  */
                .collect(Collectors.mapping(Book::getName, Collectors.joining(",")))
                ;
        // System.out.println(stringRes2);


        /*
            Esto recibe un clasifierFunction
         */

        Map<Integer, List<Book>> collect1 = myBooks.stream()
                // se agrupan los libros por año de publicación, esto puede por cualquier termino
                .collect(Collectors.groupingBy(book -> book.getYear()));
        // System.out.println(collect1);

        Map<Integer, String> collect2 = myBooks.stream()
                /*
                    se agrupan los libros por año de publicación, esto puede por cualquier termino,
                    despues de aplicar el recolector mapping.

                    primero se ejecuta la función para agrupar por año: Collectors.groupingBy(book -> book.getYear()
                    despues a cada grupo, ejecuta la función de mapeo y se queda con el nombre de cada uno Collectors.mapping(Book::getName
                    y por último coge todos los titulos de un mismo grupo y hace el joining

                    esto lo hace por cada grupo
                 */
                .collect(Collectors.groupingBy(book -> book.getYear(), Collectors.mapping(Book::getName, Collectors.joining(", "))));
        //System.out.println(collect2);


        // Recolector de reducción
        Map<Integer, Long> collect3 = myBooks.stream()
                .collect(Collectors.groupingBy(book -> book.getYear(), Collectors.counting()));
        //System.out.println(collect3);


        // Sumamos el año de los libros
        Map<Integer, Integer> collect4 = myBooks.stream()
                .collect(Collectors.groupingBy(book -> book.getYear(), Collectors.summingInt(Book::getYear)));
        // System.out.println(collect4);

        Map<Integer, Optional<Book>> collect5 = myBooks.stream()
                .collect(Collectors.groupingBy(book -> book.getYear(), Collectors.minBy(Comparator.comparing(Book::getYear))));
        // System.out.println(collect5);


        /*
            Nos devuelve una estadistica con el valor minimo, maximo, average (la media), etc.
            Interesante
         */
        Map<Integer, IntSummaryStatistics> collect6 = myBooks.stream()
                .collect(Collectors.groupingBy(book -> book.getYear(), Collectors.summarizingInt(Book::getYear)));
        //System.out.println(collect6);



        /*
            Crea dos grupos al momento de pasar un predicado,
            uno con los libros que cumplen el predicado y otro que no cumplen
         */
        Map<Boolean, List<Book>> collect7 = myBooks.stream()
                .collect(Collectors.partitioningBy(book -> book.getYear() < 2000));
        // System.out.println(collect7);


        /*
            Collectors.counting() con este parametro,
            me cuenta cuantos cumplen con el predicado y cuantos nos cumplen
         */
        Map<Boolean, Long> collect8 = myBooks.stream()
                .collect(Collectors.partitioningBy(book -> book.getYear() < 2000, Collectors.counting()));
        // System.out.println(collect8);




        /*
            RECOLECTORES CON FILTRADO
         */
        // Cuantos libros de cada género hay que sean de este siglo
        Map<Genre, Long> collect9 = myBooks.stream()
                .filter(book -> book.getYear() >= 2000)
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
        //System.out.println(collect9);


        // Primeri agrupamos y luego filtramos por cada lemento de cada grupo, counting se ejecuta despues del filtrado
        Map<Genre, Long> collect10 = myBooks.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.filtering(book -> book.getYear() >= 2000, Collectors.counting())));
        //System.out.println(collect10);


        /*
            |Libros ordenados por año
         */
        myBooks.stream()
                .sorted(Comparator.comparingInt(Book::getYear));
                // .forEach(System.out::println);

        /*
        Ordenado por titulo a la inversa
         */
        myBooks.stream()
                .sorted(Comparator.comparing(Book::getYear, Comparator.reverseOrder()));
                //.forEach(System.out::println);


        /*
            Ordenamos primero por titulo y luego por año en orden natural
         */
        myBooks.stream()
                .sorted(Comparator.comparing(Book::getName).thenComparing(Book::getYear));
                // .forEach(System.out::println);


        /*
            Ordenamos primero por titulo y luego por año en orden inverso
         */
        myBooks.stream()
                .sorted(Comparator.comparing(Book::getName).thenComparing(Book::getYear, Comparator.reverseOrder()));
                // .forEach(System.out::println);


        myBooks.stream()
                .sorted(Comparator.comparing(Book::getName).thenComparing(Book::getYear, Comparator.reverseOrder()))
                .filter(book -> book.getYear() > LocalDate.now().getYear()-10)
                .findFirst()
                .filter(book -> book.getGenre().equals(TERROR))
                // este map es de optional y no de Stream
                .map(Book::getName)
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("No se encontro un libro"));
    }
}
