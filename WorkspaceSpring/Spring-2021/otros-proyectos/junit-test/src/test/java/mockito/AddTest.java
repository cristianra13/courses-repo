package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

/*
    IMPORTANTE tener en cuenta que mockito devuelve valores por defecto 'nice', por ejemplo:
    cuando son objetos numericos, devuelve ceros, para booleanos devuelve falso, para cadenas devuelve vacío o null
 */
public class AddTest {
    // Con esto decimos que en la clase Add vamos a indicar los objetos marcados con @Mock
    @InjectMocks
    private Add addClass;

    // con está etiqueta indicamos que un objeto es un mock
    @Mock
    private ValidNumber validNumber;

    @Mock
    private Print print;

    @Captor
    private ArgumentCaptor<Integer> captor;


    @BeforeEach
    public void setUp()
    {
        // vamos a inicializar los mocks y podemos usar nuestra clase Add
        MockitoAnnotations.initMocks(this);
    }

    /*
        Vamos a testear el metodo add de la clase Add
     */
    @Test
    public void addTest()
    {
        /*
            Dentro de when, llamamos el metodo que vamos a usar del mock, en este caso validNumber.check()
            Le decimos que nos devuelva con .thenReturn(false), un booleano ya que el metodo check, devuelve esto
         */
        Mockito.when(validNumber.check(3)).thenReturn(true);
        boolean checkNumber = validNumber.check(3);
        Assertions.assertEquals(true, checkNumber);

        Mockito.when(validNumber.check("a")).thenReturn(false);
        checkNumber = validNumber.check("a");
        Assertions.assertEquals(false, checkNumber);
    }

    @Test
    public void addMockExceptionTest()
    {
        Mockito.when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos procesar 0"));
        Exception exception = null;
        try
        {
            /*
                cuando se ejecuta este metodo, realmente está lanzando la excepción o el mock
                Esto se debe a que cuando se envía cero en checkZero, este valida y lanza una Excepcion
             */
            validNumber.checkZero(0);
        } catch (ArithmeticException e)
        {
            exception = e;
        }
        Assertions.assertNotNull(exception);
    }

    /*
        Con este metodo podemos usar un metodo de la clase mockeada
     */
    @Test
    public void addRealMethodTest()
    {
        // Llama al metodo real que se encuentra en la clase ValidNumber y lanza el resultado que devuelva el metodo
        Mockito.when(validNumber.check(3)).thenCallRealMethod();
        Assertions.assertEquals(true, validNumber.check(3));

        Mockito.when(validNumber.check("a")).thenCallRealMethod();
        Assertions.assertEquals(false, validNumber.check("a"));
    }

    @Test
    public void addDoubleToIntThenAnswerTest()
    {
        /*
            Podemos mapear respuestas mas complejas
         */
        Answer<Integer> answer = new Answer<Integer>()
        {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable
            {
                return 7;
            }
        };

        Mockito.when(validNumber.doubleTOInt(7.7)).thenAnswer(answer);
        Assertions.assertEquals(14, addClass.addInt(7.7));
    }

    // Arrange
    // Action
    // Assert

    @Test
    public void patternTest()
    {
        // Arrange -- Se mockea la valdación de los dos numeros a sumar
        Mockito.when(validNumber.check(4)).thenReturn(true);
        Mockito.when(validNumber.check(5)).thenReturn(true);
        // Action
        int result = addClass.add(4,5);
        // Assert
        Assertions.assertEquals(9, result);
    }

    /*
        De está forma limitamos al test diciendole que debemos recibir en el metodo check 4 y 5
        para que nos retorne true.
     */
    @Test
    public void patternBDDTest()
    {
        // Given
        BDDMockito.given(validNumber.check(4)).willReturn(true);
        BDDMockito.given(validNumber.check(5)).willReturn(true);
        // When
        int result = addClass.add(4,5);
        // Then
        Assertions.assertEquals(9, result);
    }

    /*
        COn está forma, le decimos al mock que estamos abiertos a recibir cualquier número
        que sea entero
        con ArgumentMatchers.anyInt() podemos enviar cualquier numero entero y nos devolvera true siempre y cuando
        se respete el valor ingresado
     */
    @Test
    public void argumentMatcherTest()
    {
        // Given
        BDDMockito.given(validNumber.check(ArgumentMatchers.anyInt())).willReturn(true);
        BDDMockito.given(validNumber.check(ArgumentMatchers.anyInt())).willReturn(true);
        // When
        int result = addClass.add(4,68);
        // Then
        Assertions.assertEquals(72, result);
    }

    @Test
    public void adPrintTest()
    {
        //Given
        BDDMockito.given(validNumber.check(4)).willReturn(true);
        // when
        addClass.addPrint(4, 4);
        // pasamos el objeto a verificar y le indicamos el metodo a checkear
        // diciendole que si se está chckeando con el valor 4
        // then
        //Mockito.verify(validNumber).check(4);
        /*
            A continuación le decimos que el metodo se debe llamar al menos dos veces que es
            lo que sucede cuando se invoca en la clase add metodo addPrint donde se llama
            dos veces a validNumber.check para validación de los dos objetos
         */
        Mockito.verify(validNumber, Mockito.times(2)).check(4);
    }

    @Test
    public void adPrintNeverTest()
    {
        //Given
        BDDMockito.given(validNumber.check(4)).willReturn(true);
        // when
        addClass.addPrint(4, 4);
        /*
            A continuación le decimos que el metodo se debe llamar al menos dos veces que es
            lo que sucede cuando se invoca en la clase add metodo addPrint donde se llama
            dos veces a validNumber.check para validación de los dos objetos
         */
        Mockito.verify(validNumber, Mockito.never()).check(99);
    }

    @Test
    public void adPrintAtLeastTest()
    {
        //Given
        BDDMockito.given(validNumber.check(4)).willReturn(true);
        // when
        addClass.addPrint(4, 4);
        /*
            Con atLeast le decimos que el numero minimo de veces que se ejecuta
         */
        Mockito.verify(validNumber, Mockito.atLeast(1)).check(4);
    }

    @Test
    public void adPrintAtMostTest()
    {
        //Given
        BDDMockito.given(validNumber.check(4)).willReturn(true);
        // when
        addClass.addPrint(4, 4);
        addClass.addPrint(4, 4);
        /*
            COn atMost le decimos que se ejecuta al menos una vez
         */

        Mockito.verify(validNumber, Mockito.atLeast(4)).check(4);
    }

    @Test
    public void adPrintShowMessaheTest()
    {
        //Given
        BDDMockito.given(validNumber.check(4)).willReturn(true);
        BDDMockito.given(validNumber.check(5)).willReturn(true);
        // when
        addClass.addPrint(4, 5);
        /*
            COn atMost le decimos que se ejecuta al menos una vez
         */

        Mockito.verify(print).showMessage(9);
        Mockito.verify(print, Mockito.never()).showError(); // valida que no se ha llamado el metodo showError()
    }

    @Test
    public void captorTest()
    {
        //Given
        BDDMockito.given(validNumber.check(4)).willReturn(true);
        BDDMockito.given(validNumber.check(5)).willReturn(true);
        // when
        addClass.addPrint(4, 5);
        // then
        // capturamos el argumento con captor.capture()
        Mockito.verify(print).showMessage(captor.capture());
        // validamos que el captor va a ser igual a lo que esperamos
        Assertions.assertEquals(captor.getValue(), 9);
    }

    /*
        Uso de Spy
     */
    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    public void spyTest()
    {
        spyList.add("1");
        spyList.add("2");
        Mockito.verify(spyList).add("1");
        Mockito.verify(spyList).add("2");
        /*
            All llamar al metodo spyList.size(), este no falla ya que spy nos ayuda
            a que los metodos que no fueron mockeados, devuelvan el valor real,
            en este caso, la longitud de la lista
         */
        Assertions.assertEquals(2, spyList.size());
    }

    @Test
    public void mockTest()
    {
        mockList.add("1");
        mockList.add("2");
        Mockito.verify(mockList).add("1");
        Mockito.verify(mockList).add("2");
        /*
            con la siguiente linea presenta error ya que con @Mock no nos mockea el metodo size() de la lista
            por lo que nos devuelve el valor por defecto que es cero - 0
         */
        // Assertions.assertEquals(2, mockList.size());

        BDDMockito.given(mockList.size()).willReturn(2);
        Assertions.assertEquals(2, mockList.size());
    }


}
