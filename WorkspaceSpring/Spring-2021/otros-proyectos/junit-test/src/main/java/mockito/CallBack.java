package mockito;

public interface CallBack
{
    void onSuccess(String response);

    void onFail(String error);
}
