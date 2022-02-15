package test.programacion.funcional.imperativav2.superfunciones.clases;

import test.programacion.funcional.imperativav2.superfunciones.interfaces.Predicado;

public class SoloPares implements Predicado
{
    @Override
    public boolean test(Integer valor)
    {
        return valor % 2 == 0;
    }
}
