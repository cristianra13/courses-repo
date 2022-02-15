package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TemperatureCalculatorTest
{
    private static TemperatureCalculator temperatureCalculator;

    @BeforeAll
    public static void setup()
    {
        temperatureCalculator = new TemperatureCalculator();
    }

    @Nested
    class toFarenheitTests
    {
        @Test
        public void toFarenheitAssertEquals()
        {
            Assertions.assertEquals(59, temperatureCalculator.toFarenheit(15));
        }

        @Test
        public void toFarenheitAssertNotEquals()
        {
            Assertions.assertNotEquals(68, temperatureCalculator.toFarenheit(15));
        }
    }

    @Nested
    class toDegree
    {
        @Test
        public void toDegreeAssertEquals()
        {
            Assertions.assertEquals(5, temperatureCalculator.toDegree(41));
        }

        @Test
        public void toDegreeAssertNotEquals()
        {
            Assertions.assertNotEquals(9, temperatureCalculator.toDegree(41));
        }
    }



}
