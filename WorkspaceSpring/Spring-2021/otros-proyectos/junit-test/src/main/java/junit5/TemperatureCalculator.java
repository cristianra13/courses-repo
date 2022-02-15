package junit5;

public class TemperatureCalculator
{
    public float toFarenheit(float degree)
    {
        return (degree * 9 / 5) +32;
    }

    public float toDegree(float farenheit)
    {
        return (farenheit - 32) * 5 / 9;
    }
}
