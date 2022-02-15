package test.programacion.funcional.imperativav2.superfunciones.clases;

import test.programacion.funcional.imperativav2.superfunciones.interfaces.Funcion;

public class AlCubo implements Funcion
{
    @Override
    public Integer aplicar(Integer valor)
    {
        return valor * valor * valor;
    }
}
