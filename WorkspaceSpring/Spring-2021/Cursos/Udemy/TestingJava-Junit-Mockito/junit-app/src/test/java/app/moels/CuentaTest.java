package app.moels;

import app.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest {

    private TestInfo testInfo;
    private TestReporter testReporter;

    // ejecuta antes de todos los teste
    @BeforeAll
    static void initMetodotest() {
        System.out.println("Iniciando método ejecución");
    }

    @BeforeEach
    void initBefoEach(TestInfo testInfo, TestReporter testReporter) {
//        System.out.println("Inicio de ejecución de cada test");
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        // uso de testInfo y TestReporter
        System.out.println(
                "Ejecutando: " + testInfo.getTestMethod().get().getName() +
                " para el DisplayName: " + testInfo.getDisplayName()
                + " con los tags: " + testInfo.getTags()
        );
    }

    @Test
    // @Disabled
    @DisplayName("Test para nombre de la cuenta")
    void nombreCuentaTest() {
        // fail();
        testReporter.publishEntry("Ejecutando test " + testInfo.getTestMethod().get().getName());
        Cuenta cuenta = new Cuenta("Cristian Real", new BigDecimal(1000_0000));
        cuenta.setPersona("Cristian Real");
        String esperado = "Cristian Real";
        assertEquals(esperado, cuenta.getPersona(), "El objeto no es el mismo");
    }

    @Test
    @Tag("nombreCuentaTest")
    @DisplayName("Test para nombre de la cuenta TestInfo y testReporter")
    void nombreCuentaInyeccionDependenciasTest() {
        Cuenta cuenta = new Cuenta("Cristian Real", new BigDecimal(1000_0000));
        cuenta.setPersona("Cristian Real");
        String esperado = "Cristian Real";
        assertEquals(esperado, cuenta.getPersona(), "El objeto no es el mismo");
    }

    @Test
    void saldoCuenta() {
        Cuenta cuenta = new Cuenta("Cristian Real", new BigDecimal("1000.0000"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.0000, cuenta.getSaldo().doubleValue());
    }

    @Test
    void saldoMayorQueCeroFalseTest() {
        Cuenta cuenta = new Cuenta("Cristian Real", new BigDecimal("1000.0000"));
        assertNotNull(cuenta.getSaldo());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void saldoMayorQueCeroTrueTest() {
        Cuenta cuenta = new Cuenta("Cristian Real", new BigDecimal("1000.0000"));
        assertNotNull(cuenta.getSaldo());
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void referenciaCuentaTest() {
        Cuenta cuentaCristian = new Cuenta("Cristian", new BigDecimal("899.2356"));
        Cuenta cuentaCristian2 = new Cuenta("Cristian", new BigDecimal("899.2356"));
        Cuenta cuentaAnna = new Cuenta("Anna", new BigDecimal("750000000"));
        assertEquals(cuentaCristian2, cuentaCristian);
    }

    @Test
    void debitoCuentaTest() {
        Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
        cuenta.debidoCuenta(new BigDecimal("100"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    // debitoCuentaTest @RepiteTest
    @RepeatedTest(value = 3, name = "Repeticion Test Numero{currentRepetition} de {totalRepetitions}")
    void debitoCuentaRepiteTest(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 3) {
            System.out.println("Llegamos");
        }
        Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
        cuenta.debidoCuenta(new BigDecimal("100"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    // pruebas parametrizadas
    @Nested
    @Tag("param")
    class PruebasParametrizadas {
        @ParameterizedTest(name = "numero de repeticion {index} ejecutando con valor {0} {argumentsWithNames}")
        @ValueSource(strings = {"100", "200", "300", "500", "700", "1000"})
        void debitoCuentaParametrizedTest(String monto) {

            Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
            cuenta.debidoCuenta(new BigDecimal(monto));
            assertNotNull(cuenta.getSaldo());
            assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        }

        @ParameterizedTest(name = "numero de repeticion {index} ejecutando con valor {0} {argumentsWithNames}")
        @CsvSource({"1,100", "2,200", "3,300", "4,500", "5,700", "6,1000"})
        void debitoCuentaParametrizedValueSourceTest(String index, String monto) {
            System.out.println(index + "-" + monto);
            Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
            cuenta.debidoCuenta(new BigDecimal(monto));
            assertNotNull(cuenta.getSaldo());
            assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        }

        // esto también lo podemos hacer con un archivo .csv
        @ParameterizedTest(name = "numero de repeticion {index} ejecutando con valor {0} {argumentsWithNames}")
        @CsvSource({"200,100,Cristian, real", "250,200,Maria,Diaz", "300,300,Sandra,Perez", "510,500, Helen, Smith", "750,700, John,Doe", "1000,1000,Jhonny,Emm"})
        void debitoCuentaParametrizedValueSourceTest2(String saldo, String monto, String nombre, String apellido) {
            System.out.println(saldo + "-" + monto + "-" + nombre + "-" + apellido);
            Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
            cuenta.setSaldo(new BigDecimal(saldo));
            cuenta.debidoCuenta(new BigDecimal(monto));
            assertNotNull(cuenta.getSaldo());
            assertNotEquals(nombre, apellido);
            assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) >= 0);
        }

        @ParameterizedTest(name = "numero de repeticion {index} ejecutando con valor {0} {argumentsWithNames}")
        @CsvFileSource(resources = "/data.csv")
        void debitoCuentaParametrizedCsvSourceTest(String monto) {
            System.out.println(monto);
            Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
            cuenta.debidoCuenta(new BigDecimal(monto));
            assertNotNull(cuenta.getSaldo());
            assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        }
    }

    @ParameterizedTest(name = "numero de repeticion {index} ejecutando con valor {0} {argumentsWithNames}")
    @MethodSource("montoList")
    void debitoCuentaParametrizedMethodSourceTest(String monto) {
        System.out.println(monto);
        Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
        cuenta.debidoCuenta(new BigDecimal(monto));
        assertNotNull(cuenta.getSaldo());
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    static List<String> montoList() {
        return Arrays.asList("100", "200", "300", "400", "500", "700", "1000");
    }

    @Test
    void creditoCuentaTest() {
        Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
        cuenta.creditoCuenta(new BigDecimal("100"));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void dineroInsuficienteException() {
        Cuenta cuenta = new Cuenta("Cristian", new BigDecimal("1000.12345"));
        // mandamos un lambda para lanzar un exception en los test
        DineroInsuficienteException dineroInsuficienteException = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debidoCuenta(new BigDecimal("15000.12345"));
        });

        String messageExpected = "Dinero insuficiente en su cuenta";
        assertEquals(messageExpected, dineroInsuficienteException.getMessage());
    }

    @Test
    void transferirDineroEntreCuentasTest() {
        Cuenta origen = new Cuenta("Cristian", new BigDecimal("25000"));
        Cuenta destino = new Cuenta("Maria", new BigDecimal("15000"));

        Banco banco = new Banco();
        banco.setNombre("Banco nacional");
        banco.transferir(origen, destino, new BigDecimal("5000"));
        assertEquals(20000, origen.getSaldo().intValue());
        assertEquals(20000, destino.getSaldo().intValue());
    }

    @Test
    void relacionBancoCuentasTest() {
        Cuenta origen = new Cuenta("Cristian", new BigDecimal("25000"));
        Cuenta destino = new Cuenta("Maria", new BigDecimal("15000"));

        Banco banco = new Banco();
        banco.addCuenta(origen);
        banco.addCuenta(destino);
        banco.setNombre("Banco Nacional");
        banco.transferir(origen, destino, new BigDecimal("5000"));

        assertAll(
                () -> {
                    assertEquals(20000, origen.getSaldo().intValue());
                },
                () -> {
                    assertEquals(20000, destino.getSaldo().intValue());
                },
                () -> {
                    assertEquals(2, banco.getCuentas().size());
                },
                () -> {
                    assertEquals("Banco Nacional", origen.getBanco().getNombre());
                },
                () -> {
                    assertEquals("Cristian", banco.getCuentas().stream()
                            .filter(cuenta -> cuenta.getPersona().equals("Cristian"))
                            .findFirst()
                            .get().getPersona());
                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .anyMatch(cuenta -> cuenta.getPersona().equals("Cristian")));
                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .anyMatch(cuenta -> cuenta.getPersona().equals("Maria")));
                }
        );
    }

    //  Test sobre un SO en especifico
    @Nested
    @Tag("testsSO")
    class SistemaOperativoTest {
        @Test
        @EnabledOnOs(OS.WINDOWS)
        void soloWindows() {
            System.out.println("Solo Windows");
        }

        @Test
        @EnabledOnOs({OS.LINUX, OS.MAC})
        void soloLinuxYMac() {
            System.out.println("Solo Linux y Mac");
        }

        @Test
        @DisabledOnOs(OS.WINDOWS)
        void onWindows() {
            System.out.println("Ejecución sobre windows");
        }
    }

    @Nested
    @Tag("JavaVersions")
    class JavaVersiontest {
        // test sobre una versión de Java

        @Test
        @EnabledOnJre(JRE.JAVA_8)
        void soloJava8() {
            System.out.println("Esto es olo sobre Java 8");
        }

        @Test
        @EnabledOnJre(JRE.JAVA_11)
        void soloJava11() {
            System.out.println("Esto es olo sobre Java 11");
        }

        @Test
        @DisabledOnJre(JRE.JAVA_11)
        void onJre11() {
            System.out.println("Ejecución sobre java 11");
        }
    }

    @Nested
    @Tag("SystemPropertiesTests")
    @Tag("System")
    class SystemPrpertiesTest {
        // imprimir propiedades del sistema
    /*@Test
    void printSystemProperties() {
        Properties properties = System.getProperties();
        properties.forEach( (key, value) -> System.out.println("key: " + key + " value: " + value));
    }*/

        @Test
        //@EnabledIfSystemProperty(named = "java.version", matches = "11.0.7")
        @EnabledIfSystemProperty(named = "java.version", matches = ".*11.*") // con regex
        void javaVersion() {
            System.out.println("Propiedad encontrada!");
        }

        // deshabilitar si la arquitectura es de 32 bits
        @Test
        @DisabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
        void solo64bitsSO() {

        }

        // deshabilitar si la arquitectura es de 32 bits
        @Test
        @EnabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
        void solo32bitsSO() {

        }

        // ejecutar solo  cuando sea desarrollo
        @Test
        @EnabledIfSystemProperty(named = "ENV", matches = "dev")
        void ifEnvisDev() {

        }
    }

    @Nested
    @Tag("VariablesEnvronment")
    @Tag("System")
    class VarEnvironmentTest {
        // variables de ambiente del sistema
        @Test
        void prinEnvVariables() {
            Map<String, String> getenv = System.getenv();
            getenv.forEach((var, val) -> System.out.println(var + " : " + val));
        }


        @Test
        @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = ".*jdk1.8.0_271.*")
        void ifjavaHomeExists() {

        }
    }

    @Nested
    class AssumptionsTest {
        // Supocisiones

        // solo Se ejecuta prueba si el ambiente de desarrollo es DEV
        @Test
        void saldoCuentaDevTest() {
            boolean esDev = "dev".equals(System.getProperty("ENV"));
            System.out.println(System.getProperty("ENV"));
            // si esto es false, no se ejecuta el resto
            assumeTrue(esDev);
            Cuenta cuenta = new Cuenta("Cristian Real", new BigDecimal("1000.0000"));
            assertNotNull(cuenta.getSaldo());
            assertEquals(1000.0000, cuenta.getSaldo().doubleValue());
        }

        // Si se cumple validación, se ejecuta cierto código
        @Test
        void saldoCuentaDev2Test() {
            boolean esDev = "dev".equals(System.getProperty("ENV"));
            System.out.println(System.getProperty("ENV"));
            // si esto es false, no se ejecuta el resto
            assumingThat(esDev, () -> {
                // código que se habilita si la validación es exitosa
                Cuenta cuenta = new Cuenta("Cristian Real", new BigDecimal("1000.0000"));
                assertNotNull(cuenta.getSaldo());
                assertEquals(1000.0000, cuenta.getSaldo().doubleValue());
            });
        }
    }

    @Nested
    @Tag("timeOutTests")
    @Disabled
    class TimeOutTests{
        @Test
        @Timeout(2) // recibe la cantidad de segundos
        void timeOutTest() throws InterruptedException {
            TimeUnit.SECONDS.sleep(3);
        }

        @Test
        @Timeout(value = 500, unit = TimeUnit.MILLISECONDS) // recibe la cantidad de segundos
        void timeOutTest2() throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
        
        // de forma programatica

        @Test
        void timeOutWithAssert() {
            assertTimeout(Duration.ofSeconds(2), () -> {
                TimeUnit.SECONDS.sleep(3);
            });
        }
    }
    
    
    
    /*@BeforeEach
    void beforeEachTest() {
        System.out.println("Finalización de cada test");
    }*/

    // ejecuta al finalizar todos los teste
    @AfterAll
    static void testFinalizacionMetodos() {
        System.out.println("Finalización de todos métodos");
    }
}