package test.programacion.funcional.v11_streams;

// Forma funcional

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        // esto para no hacer los metodos estaticos
        new Main();
    }

    public Main()
    {
        List<String> nombres = new ArrayList<>(List.of("Cristian", "Manolo", "Anna"));
        // 1. Funcion generadira del Sream
        nombres.stream()
                //2. 0 o mas operaciones intermedias
                .filter(nombre -> nombre.contains("C"))
                .collect(Collectors.toList());
                //.forEach(System.out::println);

        Stream
                // la fuente de datos de generate() es infinita
                .generate(() -> new Random().nextInt(10))
                .limit(50);
                //.forEach(System.out::println);

        Stream
                // está función recibe el valor inicial, el predicado y el UnaryOperator
                .iterate(1, x -> x < 100, x -> x * 5);
                //.forEach(System.out::println);

        // números aleatorios del 1 al 100 con Stream, solo 10 números
        new Random().ints(10, 1, 100);
                //.forEach(System.out::println);


        // Genera los número de 1 a 9, acá el limite que es 10 es exclusivo
        IntStream.range(1, 10);
                //.forEach(System.out::println);

        // genera los numeros del 1 al 10, acá el limite que es 10 es inclusivo
        IntStream.rangeClosed(1, 10);
                //.forEach(System.out::println);


        /*
            va eliminando los elementos que cumplan con el predicado dado,
            o los que sean pares en este caso, serán eliminados.
            Una vez encuentre alguno que NO cumpla con el predicadom de ahí en adelante agrega todos
            los demas ya sea que cumplan o no
         */
        Stream.of(2,4,6,8,9,10,11,13)
                .dropWhile(n -> n % 2 == 0 );
                //.forEach(System.out::println);


        /*
            En este caso, se muestran los que cumplan con el predicado,
            en el momento que encuentre alguno que no cumple,
            de ahí en adelante no se muestran o no se evaluan
         */
        Stream.of(2,4,6,8,9,10,11,13)
                .takeWhile(n -> n % 2 == 0);
                // .forEach(System.out::println);

        /*
            Operaciones de transformación
         */
        Stream.of(1,2,3)
                .map(n -> n + n); // recibe una function
                //.forEach(System.out::println);

        // nos devuelve un DoubleStream
        Stream.of(1,2,3)
                .mapToDouble(n -> n + n); // recibe una function
                // .forEach(System.out::println);


        Stream.of(2, 4, 6)
                // flatMap() retorna un Stream de r = Stream<R>, R es el tipo de respuesta
                /*
                    el flatmap lo usaremos cuando el retorno de la lambda no sea de tipo normal
                    si no que sea de un Stream de un tipo
                 */
                .flatMap(this::getRandomNumbers);
                //.forEach(System.out::println);


        Stream.of(2,4,6)
                .flatMap(this::getRandomNumbers)
                /*
                     peek nos permite realizar una accion con cada elemento pero
                     a diferencia de forEach, este si retorna un Stream formado por los mismos
                     elementos del Stream sobre el que se ejecuta

                     Sirve como un actuador
                 */
                .peek(System.out::println)
                .map(v -> v * v);
                /*
                    Importante: es que no hay ninguna garantia que se vayan a ejecutar en orden
                    los elementos en el foreach
                 */
                // .forEach(System.out::println);

        Stream.of(2,4,6)
                /*
                Cuando ejecutamos en paralelo, cada valor se ejecuta en unhilo independiente,
                por lo que no se tiene garantia del orden de este
                 */
                .parallel();
                //.forEach(System.out::println);

        Stream.of(2,4,6)
                .parallel();
                /*
                    Este es menos optimo en rendimiento, pero garantiza el orden de los valores
                 */
                // .forEachOrdered(System.out::println);


        /*
            Un ejemplo para pasa una lista a otra por valor y NO por referencia
         */
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);

        List<Integer> newlista = new ArrayList<>();
        for(Integer val : lista) newlista.add(val);
        lista.add(8);
        newlista.add(12);


        Stream.of(2,4,6)
                .sorted(Comparator.reverseOrder());
                //.forEach(System.out::println);



        /*
            Metodos de reducción.
            Consiste en un unico valor a partir de todos los valores manejados por el Stream
            - Estos son operaciones terminales ya que no retornan ningún Stream.

         */

        long count = Stream.of(1, 2, 3)
                .count();
        //System.out.println(count);

        count = IntStream.range(1 , 11)
                .sum();


        int sum = Stream.of(1, 2, 3)
                .mapToInt(Integer::intValue)
                .sum();
        //System.out.println(sum);

        OptionalInt min = IntStream.of(1, 2, 3, 4,5)
                .filter(valor -> valor % 2 == 8)
                .min();
        // System.out.println(min.isPresent() ? min.getAsInt() : 0);

        Stream.of(2,3,4)
                .filter(valor -> valor >= 1)
                .reduce((acumulador, valor) -> acumulador * valor);
                //.ifPresentOrElse(System.out::println,
                 //       () -> System.out.println("Valor no presente"));

        Integer reduce = Stream.of(2, 3, 4)
                .filter(valor -> valor >= 1)
                // 2 es el valor inicial del acumulador, por lo que sería inicialmente 2 * 2 = 4, 4 * 3 = 12, 12 * 4 = 48
                .reduce(2, (acumulador, valor) -> acumulador * valor);
        //System.out.println(reduce);




        /*

         */

        Integer suma = Stream.of(
                new Student("Crsitian", 27),
                new Student("Juana", 22),
                new Student("Bartolomeo", 45))
                /*
                    Como primer parametroi recibe un identificador,

                    como segundo parametro recibe una BiFunction la cual recibe un tipop T, U y retorna un tipo R, esto permite que los dos parametros de entrada sean de
                    diferente tipo

                    como tercer parametro, recibe un BinaryOperator el cual recibe dos parametros del mismo tipo y retorna uno del mismo tipo.
                 */
                .reduce(0, (acumulador, student) -> acumulador + student.getAge(), (parcial1, parcial2) -> parcial1 + parcial2);
        System.out.println( "Final result: " + suma);


    }

    public Stream<Integer> getRandomNumbers(Integer size)
    {
        return new Random().ints(size, 0, 10).boxed();
    }
}
