package test.programacion.funcional.imperativav2.superfunciones.clases;

import test.programacion.funcional.imperativav2.superfunciones.interfaces.Consumidor;

public class Impresor implements Consumidor
{
    @Override
    public void aceptar(Integer valor)
    {
        System.out.println(valor);
    }
}
