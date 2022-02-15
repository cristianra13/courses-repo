package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class LoginTest {
    @InjectMocks
    private Login login;

    /*
        Se mockea webservice ya que la clase Login depende de está para funcionar
     */
    @Mock
    private WebService webService;

    @Captor
    private ArgumentCaptor<CallBack> callBackArgumentCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doLoginOnSuccessTest()
    {
        Mockito.doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                String user = (String) invocation.getArguments()[0]; // Decimos que el parametro user va en la posicion 0
                Assertions.assertEquals("cristian", user);
                String password = (String) invocation.getArguments()[1]; // Decimos que el parametro password va en la posicion 1
                Assertions.assertEquals("123456", password);
                CallBack callBack = (CallBack) invocation.getArguments()[2]; // Decimos que el parametro callBack va en la posicion 2
                callBack.onSuccess("OK");
                return null;
            }
            /*
                Decimos que cuando se detecte que llame al metodo login de webservice se presentara la anterior validación
                y que se recibe cualquier usuario, password y callBack, siempre y cuando el tipo de dato sea el que se espera
                en el metodo
             */
        }).when(webService).login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(CallBack.class));

        /*
            Ejecutamos el metodo doLogin para que se puede hacer el llamado al doAnswer
         */
        login.doLogin();
        Mockito.verify(webService, Mockito.times(1)).login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(CallBack.class));
        Assertions.assertEquals(login.isLogin, true);
    }

    @Test
    public void doLoginOnFailTest()
    {
        Mockito.doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                String user = (String) invocation.getArguments()[0]; // Decimos que el parametro user va en la posicion 0
                Assertions.assertEquals("cristian", user);
                String password = (String) invocation.getArguments()[1]; // Decimos que el parametro password va en la posicion 1
                Assertions.assertEquals("123456", password);
                CallBack callBack = (CallBack) invocation.getArguments()[2]; // Decimos que el parametro callBack va en la posicion 2
                callBack.onFail("ER");
                return null;
            }
            /*
                Decimos que cuando se detecte que llame al metodo login de webservice se presentara la anterior validación
                y que se recibe cualquier usuario, password y callBack, siempre y cuando el tipo de dato sea el que se espera
                en el metodo
             */
        }).when(webService).login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(CallBack.class));

        /*
            Ejecutamos el metodo doLogin para que se puede hacer el llamado al doAnswer
         */
        login.doLogin();
        Mockito.verify(webService, Mockito.times(1)).login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(CallBack.class));
        Assertions.assertEquals(login.isLogin, false);
    }

    @Test
    public void doLoginCaptorTest()
    {
        login.doLogin();
        Mockito.verify(webService, Mockito.times(1)).login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), callBackArgumentCaptor.capture());

        CallBack callBack = callBackArgumentCaptor.getValue();
        callBack.onSuccess("OK");
        Assertions.assertEquals(login.isLogin, true);

        callBack.onFail("ER");
        Assertions.assertEquals(login.isLogin, false);
    }
}
