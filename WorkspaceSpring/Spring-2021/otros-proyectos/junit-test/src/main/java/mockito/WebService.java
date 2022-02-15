package mockito;

public class WebService
{
    public void login(String user, String password, CallBack callBack)
    {
        if(checkLogin(user, password))
        {
            callBack.onSuccess("Usuario correcto!");
        }
        else
        {
            callBack.onFail("Usuario o clave invalida");
        }
    }


    /*
        Está sería la petición al servidor que se ha mockeado
     */
    public boolean checkLogin(String user, String password)
    {
        if(user.equals("cristian") && password.equals("123456"))
        {
            return true;
        }
        return false;
    }
}
