package test.programacion.funcional.v2_superfunciones_clases.clases;

import test.programacion.funcional.v2_superfunciones_clases.interfaces.Consumidor;

public class Impresor implements Consumidor
{
    @Override
    public void aceptar(Integer valor)
    {
        System.out.println(valor);
    }
}
