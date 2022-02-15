package test.programacion.funcional.v2_superfunciones_clases.clases;

import test.programacion.funcional.v2_superfunciones_clases.interfaces.Proveedor;

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
