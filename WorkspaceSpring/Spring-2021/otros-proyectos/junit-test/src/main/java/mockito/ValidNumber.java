package mockito;

public class ValidNumber
{
    public ValidNumber()
    { }

    public boolean check(Object  o)
    {
        if(o instanceof Integer)
        {
            if((Integer)o < 10 && (Integer)o >= 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean checkZero(Object o)
    {
        if(o instanceof Integer)
        {
            if((Integer)o >= 0)
            {
                throw new ArithmeticException("No se puede recibir cero");
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public int doubleTOInt(Object o)
    {
        if(o instanceof Double)
        {
            return ((Double)o).intValue();
        }
        else
        {
            return 0;
        }
    }
}
