package mockito;

public class Login
{
    public WebService webService;
    public boolean isLogin;

    public Login(WebService webService)
    {
        this.webService = webService;
        isLogin = false;
    }

    /*
        Metodo que hace la parte de llamado a un WebService
     */
    public void doLogin()
    {
        webService.login("cristian", "123456", new CallBack() {
            @Override
            public void onSuccess(String response)
            {
                System.out.println(response);
                isLogin = true;
            }

            @Override
            public void onFail(String error)
            {
                System.out.println(error);
                isLogin = false;
            }
        });
    }
}
