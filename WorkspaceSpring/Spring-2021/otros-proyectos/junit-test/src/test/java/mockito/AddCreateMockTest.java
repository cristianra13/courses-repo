package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
/*
    Para usar los tests con mockito, basicamente se hacen 3 pasos:
    1.  Se mockean las dependencias de la clase bajo test, en este caso la clase bajo test o a testear es ka clase Add
        y está tiene una dependencia de ValidNumber y necesitamos mockear ValidNumber.
        Si la clase Add dependiera de otras clases, estás también deberían ser mockeadas
    2.  Ejecutar el código de la clase bajo test.
    3. Validar que el código ejecutado es el esperado.
 */
public class AddCreateMockTest
{
        private Add addClass;
        private ValidNumber validNumber;

    @BeforeEach
    public void setUp()
    {
        // creamos el mock de la clase ValidNumber
        validNumber = Mockito.mock(ValidNumber.class);
        // Ahora, el validNumber que se pasa por constructor a la clase Add, no es un objeto real si no un mock que se creo anteriormente
        addClass = new Add(validNumber);
    }

    @Test
    public void addTest()
    {
        /*
            Se presenta error al validar el número dos, ya que cuando hace el check del 3 devuelve false,
            por lo que ya no valida el número 2 debido al resultado anterior
         */
        addClass.add(3,2);
        Mockito.verify(validNumber).check(3);
        Mockito.verify(validNumber).check(2);
    }
}
