package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidNumberTest
{
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp()
    {
        validNumber = new ValidNumber();
    }

    @AfterEach
    public void tearDown()
    {
        validNumber = null;
    }

    @Test
    public void checkPositiveTest()
    {
        Assertions.assertEquals(true, validNumber.check(8));
    }

    @Test
    public void checkNegativeTest()
    {
        Assertions.assertEquals(false, validNumber.check(-8));
    }

    @Test
    public void checkNoNumberTest()
    {
        Assertions.assertEquals(false, validNumber.check("a"));
    }

    @Test
    public void checkNumberZeroTest()
    {
        Assertions.assertEquals(true, validNumber.checkZero(-56));
    }

    @Test
    public void checkNumberZeroStringTest()
    {
        Assertions.assertEquals(false, validNumber.checkZero("a"));
    }

    @Test
    public void checkNumberStringZeroTest()
    {
        Assertions.assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
    }

    @Test
    public void doubleToIntTest()
    {
        Assertions.assertEquals(9, validNumber.doubleTOInt(9.99999));
    }

    @Test
    public void doubleToIntErrorTest()
    {
        Assertions.assertEquals(0, validNumber.doubleTOInt("9.99999"));
    }
}
