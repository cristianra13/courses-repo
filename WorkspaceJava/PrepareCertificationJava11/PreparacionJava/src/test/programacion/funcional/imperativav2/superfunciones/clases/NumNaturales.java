package test.programacion.funcional.imperativav2.superfunciones.clases;

import test.programacion.funcional.imperativav2.superfunciones.interfaces.Proveedor;

import java.util.Random;

public class NumNaturales implements Proveedor
{
    private static int next = 0;

    // provee un entero
    @Override
    public Integer obtener()
    {
        return next++;
    }
}
