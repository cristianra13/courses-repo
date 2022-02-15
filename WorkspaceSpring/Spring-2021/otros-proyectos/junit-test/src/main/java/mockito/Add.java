package mockito;

public class Add
{
    private ValidNumber validNumber;
    private Print print;

    public Add(ValidNumber validNumber)
    {
        this.validNumber = validNumber;
    }

    public Add(ValidNumber validNumber, Print print)
    {
        this.validNumber = validNumber;
        this.print = print;
    }

    public int add(Object a, Object b)
    {
        if(validNumber.check(a) && validNumber.check(b))
        {
            return (Integer) a + (Integer) b;
        }
        return 0;
    }

    public int addInt(Object a)
    {
        return validNumber.doubleTOInt(a) + validNumber.doubleTOInt(a);
    }

    public void addPrint(Object a, Object b)
    {
        if(validNumber.check(a) && validNumber.check(b))
        {
            int result = (Integer) a + (Integer) b;
            print.showMessage(result);
            return; // termina la ejecución del metodo
        }
        print.showError();
    }
}
