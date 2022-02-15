package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AddCreateMock2Test
{
    // Con esto decimos que en la clase Add vamos a indicar los objetos marcados con @Mock
    @InjectMocks
    private Add addClass;

    // con está etiqueta indicamos que un objeto es un mock
    @Mock
    private ValidNumber validNumber;


    @BeforeEach
    public void setUp()
    {
        // vamos a inicializar los mocks y podemos usar nuestra clase Add
        MockitoAnnotations.initMocks(this);
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
    }
}
