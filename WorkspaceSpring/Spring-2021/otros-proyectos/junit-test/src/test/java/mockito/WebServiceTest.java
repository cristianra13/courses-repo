package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WebServiceTest
{
    private WebService webService;

    /*
        La interface CallBack haría el papel de webservice o servicio a llamar o consumir
     */
    @Mock
    private CallBack callBack;

    @BeforeEach
    public void setUp()
    {
        webService = new WebService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkLoginTrueTest()
    {
        Assertions.assertTrue(webService.checkLogin("cristian", "123456"));
    }

    @Test
    public void checkLoginFalseTest()
    {
        Assertions.assertFalse(webService.checkLogin("cristian", "1235"));
    }

    @Test
    public void loginTest()
    {
        webService.login("cristian", "123456", callBack);
        Mockito.verify(callBack).onSuccess("Usuario correcto!");
    }

    @Test
    public void loginFailTest()
    {
        webService.login("cristian", "123", callBack);
        Mockito.verify(callBack).onFail("Usuario o clave invalida");
    }
}
