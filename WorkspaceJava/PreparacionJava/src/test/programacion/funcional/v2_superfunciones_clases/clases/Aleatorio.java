package test.programacion.funcional.v2_superfunciones_clases.clases;

import test.programacion.funcional.v2_superfunciones_clases.interfaces.Proveedor;

import java.util.Random;

public class Aleatorio implements Proveedor
{
    Random random = new Random();

    // provee un entero
    @Override
    public Integer obtener()
    {
        return random.nextInt(10);
    }
}
