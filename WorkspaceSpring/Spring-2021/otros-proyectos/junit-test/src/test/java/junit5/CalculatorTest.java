package junit5;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

public class CalculatorTest
{
    private Calculator calculator;

    // Se ejecuta una sola vez al principio de todoo, este debe ser estatico
    @BeforeAll
    public static void testBeforeAll()
    {
        System.out.println("ejecucion de metodo testBeforeAll()");
    }

    // Ejecuta antes de cada metodo
    @BeforeEach
    public void setUp()
    {
        calculator = new Calculator();
    }


    @Test
    public void sumarNotNullTest()
    {
        Assertions.assertNotNull(calculator,"Calculator debe ser null");
    }

    @Test
    public void sumarAssertEqualsTest()
    {
        Assertions.assertEquals(17, calculator.sumar(8, 9));
    }

    @Test
    public void sumarAssertNotEqualsTest()
    {
        Assertions.assertNotEquals(26, calculator.sumar(8, 9));
    }

    @Test
    public void sumarAssertEqualsDeltaTest()
    {
        // Se pasa al final un delta donde se le dice que el resultado puede estar entre 17 y 17.15
        Assertions.assertEquals(17, calculator.sumar(8, 9), 17.5);
    }

    @Test
    public void restarTest()
    {
        Assertions.assertEquals(19, calculator.restar(25,6));
    }


    @Test
    public void dividirEqualsTest()
    {
        Assertions.assertEquals(5, calculator.dividir(25,5));
    }

    @Test
    public void dividirNotEqualsTest()
    {
        Assertions.assertNotEquals(9, calculator.dividir(25,5));
    }

    @Test
    public void dividirByZeroExpectedExceptionTest()
    {
        // Controlamos la excepcion que lanza el código
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.dividirXCero(25,0), "No se puede dividir por cero");
    }

    // deshabilitar algunos metodos de test
    @Disabled("Deshabilitado por error en código")
    @Test
    public void dividirCeroDisable()
    {
        Assertions.assertEquals(2, calculator.dividir(12,0));
    }

    @Test
    @DisplayName("Metodo dividir  -> funcionando") // Cambia el nombre del metodo por el que este acá a la hora que se ejecuten
    public void dividirSuccessfulExpectedName()
    {
        Assertions.assertEquals(2, calculator.dividir(10,5));
    }

    @Test
    public void sumarAssertAll()
    {
        // Este metodo se maneja con lambdas, si encuentra un test que no pase, sigue ejecutando de todas maneras
        Assertions.assertAll(
                () -> Assertions.assertEquals(30, calculator.sumar(15,15)),
                () -> Assertions.assertEquals(44, calculator.sumar(22, 22)),
                () -> Assertions.assertEquals(50, calculator.sumar(24, 26))


        );
    }

    /*
    De está manera sirve para organizar los test que son para un solo metodo
    sobre todoo cuando el mismo metodo cuenta con muchos tests
     */
    @Nested
    class addTest
    {
        @Test
        public void sumarNumerosPositivos()
        {
            Assertions.assertEquals(30, calculator.sumar(15,15));
        }

        @Test
        public void sumarNumerosNegativos()
        {
            Assertions.assertEquals(-30, calculator.sumar(-15,-15));
        }

        @Test
        public void sumarNumerosCero()
        {
            Assertions.assertEquals(0, calculator.sumar(0,0));
        }
    }



    // se ejecuta despues de cada metodo
    @AfterEach
    public void tearDown()
    {
        calculator = null;
    }

    // Se ejecuta una sola vez al finalizar de todos los tests, este debe ser estatico
    @AfterAll
    public static void testAfterAll()
    {
        System.out.println("ejecucion de metodo testAfterAll()");
    }




    /*
        Pruebas parametrizadas
        Consiste en realizar la misma prueba con diferentes valores una y otra vez
     */
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("sumPrividedData") // con esto le decimos de donde vamos a obtener la data para las pruebas
    public void sumarParameterizedTest(int a, int b, int sum)
    {
        Assertions.assertEquals(sum, calculator.sumar(a,b));
    }

    private static Stream<Arguments> sumPrividedData()
    {
        /*
            está información puede provenir de una fuente de datos como:
            Servicios,
            Bases de datos,
            Archivos
         */
        return Stream.of(
                Arguments.of(6,2,8), // decimos que sume 6 + 2 y el resultado debe ser 8, y así con los demas
                Arguments.of(-6,-2,-8),
                Arguments.of(6,4,10),
                Arguments.of(230,770,1000),
                Arguments.of(0,0,0)
        );
    }


    /*
        Timeout para test que pueden tardar mucho
     */
    @Test
    public void timeOutTest()
    {
        // Espera 2 segundos antes de abortar el test
        Assertions.assertTimeout(Duration.ofMillis(2000), () -> calculator.longTaskOperation());
    }

}
