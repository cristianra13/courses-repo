package test.programacion.funcional.v12_collect;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamDeOptional {

    public static void main(String[] args) {
        new StreamDeOptional();
    }

    public StreamDeOptional(){
        List<String> ids  = List.of("345-898", "346-448", "no-existe");
        List<Book> listaLibros = ids.stream()
                .map(this::findById)
                .filter(Optional::isPresent)
                // en este caso si es seguro usar get() ya que filtramos y sabemos que tenemos los Optional presentes
                .map(Optional::get)
                .collect(toList());
        // System.out.println(listaLibros);


        /*
            A partir de Java 9 se inserto un nuevo método que nos permite reducir lo anterior
         */
        ids.stream()
                .map(this::findById)
                /*
                    FlatMap ejecuta la function que se le pasa y retorna un Stream
                    una vez que ha obtenido todos los Streams, los aplana, es decir,
                    convierte esa lista de Streams en un unico Stream de los elementos.

                    Crea un Stream con el valor que este presente en el Optional.
                    en caso de que no haya un valor presente, creara un Stream vacío para
                    ese elemento
                 */
                .flatMap(Optional::stream)
                // en este caso si es seguro usar get() ya que filtramos y sabemos que tenemos los Optional presentes

                .collect(toList());



        List<Book> myBooks = Arrays.asList(
                new Book("345-898", "El sufrimiento del hada", 2018, Genre.TERROR),
                new Book("345-900", "Halcon", 1990, Genre.THRILLER),
                new Book("346-700", "En la nada", 1815, Genre.TERROR),
                new Book("346-448", "Bababoda", 2010, Genre.COMEDY),
                //new Book("586-393", "Zombieland", 2020, Genre.THRILLER),
                new Book("346-700", "En la nada", 1815, Genre.TERROR),
                new Book("586-395", "Zombieland 2", 2020, Genre.THRILLER));


        /*
            mapping permites hace4r una determinada function antes de recolectarlos
         */

        Map<Integer, List<String>> collect = myBooks.stream()
                .collect(groupingBy(Book::getYear, mapping(Book::getName, toList())));
        // System.out.println(collect);


        /*
            COn este recolector primero se recolecta y luego transforma la información

            la diferencia con mapping, es que mapping primero tranforma y luego recolecta
         */
        String collect1 = myBooks.stream().collect(collectingAndThen(counting(), valor -> valor + " Books"));
        //System.out.println(collect1);

        /*
            Hay un recolector que aparece a partir de la versión 12 de Java
            el cual hace una recolección doble y combinación
         */

        /*
            teeing() recibe tres cosas
            1. un primer downStreamCollector() -> un primer recolector
            2. Otro recolector
            3. Por último recibe un BiFunction<T,U,R>, este recibe el resultado de los dos recolectores anteriores
                y los pasa como argumento a la BiFunction y genera un único resultado

                maxOptional.map() se ejecuta solamente si maxOptional contiene un valor,
                en esta parte el get() del optional es seguro ya que minOptional si viene con un valor

            myBooks.stream()
                .collect(teeing(maxBy(Integer::compare),
                                minBy(Intetger::compare),
                                (maxOptional, minOptional) -> maxOptional.map(max -> max - minOptional.get())));
         */


        /*
            Objetivo, Obtener todos los generos de libros ordenamos de mayor a menor número de libros,
            pero quedandonos con sólo los generos que tengan mas de 1 libro
         */
        Map<Genre, Long> collect2 = myBooks.stream()
                .collect(groupingBy(Book::getGenre, counting()))
                // retorna un conjunto de Set con todas las entradas del mapa
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                // por cada entrada me quiero quedar con su getKey()
                // .collect(toMap(entry -> entry.getKey(), entry -> entry.getValue()));
                /*
                    new LinkedHashMap<>() va a tener en cuenta el orden de los valores
                 */
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (entry1, entry2) -> entry1, () -> new LinkedHashMap<>()));
        System.out.println(collect2);

    }


    public Optional<Book> findById(String id){
        List<Book> myBooks = Arrays.asList(
                new Book("345-898", "El sufrimiento del hada", 2018, Genre.TERROR),
                new Book("345-900", "Halcon", 1990, Genre.THRILLER),
                new Book("346-700", "En la nada", 1815, Genre.TERROR),
                new Book("346-448", "Bababoda", 2010, Genre.COMEDY),
                new Book("586-393", "Zombieland", 2020, Genre.THRILLER),
                new Book("346-700", "En la nada", 1815, Genre.TERROR),
                new Book("586-395", "Zombieland 2", 2020, Genre.THRILLER));

        return myBooks.stream().
                filter(book -> book.getId().equals(id))
                .findFirst();
    }


}
