package test.programacion.funcional.v2_superfunciones_clases.clases;

import test.programacion.funcional.v2_superfunciones_clases.interfaces.Predicado;

public class SoloImpares implements Predicado
{
    @Override
    public boolean test(Integer valor)
    {
        return valor % 2 != 0;
    }
}
