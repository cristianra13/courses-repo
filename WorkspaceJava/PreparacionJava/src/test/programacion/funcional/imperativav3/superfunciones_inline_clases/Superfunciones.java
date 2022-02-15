package test.programacion.funcional.imperativav3.superfunciones_inline_clases;



import test.programacion.funcional.imperativav3.superfunciones_inline_clases.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Superfunciones
{

    public static List<Integer> proveer(int size, Proveedor proveedor)
    {
        List<Integer> lista = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            lista.add(proveedor.obtener());
        }

        return lista;
    }

    public static List<Integer> filtrar(List<Integer> valores,  Predicado predicado)
    {
        List<Integer>  resultado = new ArrayList<>();
        for(Integer var : valores)
        {
            if(predicado.test(var))
            {
                resultado.add(var);
            }
        }
        return resultado;
    }

    public static List<Integer> transformar(List<Integer> valores, Funcion funcion)
    {
        List<Integer> lista = new ArrayList<>();
        for(Integer val : valores)
        {
            lista.add(funcion.aplicar(val));
        }

        return lista;
    }

    public static List<Integer> actuar(List<Integer> valores, Consumidor consumida)
    {
        for(Integer valor : valores)
        {
            consumida.aceptar(valor);
        }
        return  valores;
    }

    public static void consumir(List<Integer> valores, Consumidor consumidor)
    {
        for(Integer valor : valores)
        {
            consumidor.aceptar(valor);
        }
    }

    public static Integer reducir(List<Integer> valores, Integer identidad, FuncionBinaria funcionBinaria)
    {
        int total = identidad;
        for(Integer valor : valores)
        {
            total = funcionBinaria.aplicar(total, valor);
        }
        return total;
    }
}
