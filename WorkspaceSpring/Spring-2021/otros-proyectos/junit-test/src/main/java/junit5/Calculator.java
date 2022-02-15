package junit5;

public class Calculator
{
    private int resultado;

    int sumar(int numero1, int numero2)
    {
        resultado = numero1 + numero2;
        return resultado;
    }

    int restar(int numero1, int numero2)
    {
        resultado = numero1 - numero2;
        return resultado;
    }

    int multiplicar(int numero1, int numero2)
    {
        resultado = numero1 * numero2;
        return resultado;
    }

    int dividir(int numero1, int numero2)
    {
        resultado = numero1 / numero2;
        return resultado;
    }

    int dividirXCero(int numero1, int numero2)
    {
        if (numero2 == 0)
        {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        resultado = numero1 / numero2;
        return resultado;
    }



    /*
        Metodo para prueba de time out
     */
    public void longTaskOperation()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
