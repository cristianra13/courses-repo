package test.programacion.funcional.v10_optional;

public class NumberUtils
{
    /*
        Creamos constructos privado para que no puedan crear una instancia de la clase
     */
    private NumberUtils(){}

    public static boolean esPrimo(int valor)
    {
        // filtramos por números primos, si encuentro un número que divida a a valor, este ya no es primo
        for (int i = 2; i < valor; i++)
        {
            if(valor % i == 0)
            {
                // si la lambda retorna false, el valor no sera incluido en el listado resultante
                return false;
            }
        }
        return true;
    }

    public static int elevarAlCuadrado(int valor)
    {
        System.out.println("Eleva: " + valor);
        return (int) Math.pow(valor, 2);
    }
}
