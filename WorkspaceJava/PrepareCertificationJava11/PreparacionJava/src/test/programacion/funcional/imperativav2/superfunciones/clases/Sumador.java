package test.programacion.funcional.imperativav2.superfunciones.clases;

import test.programacion.funcional.imperativav2.superfunciones.interfaces.FuncionBinaria;

public class Sumador implements FuncionBinaria
{
    @Override
    public Integer aplicar(Integer valor1, Integer valor2)
    {
        return valor1 + valor2;
    }
}
