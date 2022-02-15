package test.programacion.funcional.v2_superfunciones_clases.clases;

import test.programacion.funcional.v2_superfunciones_clases.interfaces.Funcion;

public class AlCuadrado implements Funcion
{
    @Override
    public Integer aplicar(Integer valor)
    {
        return valor * valor;
    }
}
