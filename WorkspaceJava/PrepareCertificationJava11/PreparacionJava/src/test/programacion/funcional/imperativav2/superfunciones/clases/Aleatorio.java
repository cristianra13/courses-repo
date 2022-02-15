package test.programacion.funcional.imperativav2.superfunciones.clases;

import test.programacion.funcional.imperativav2.superfunciones.interfaces.Proveedor;

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
