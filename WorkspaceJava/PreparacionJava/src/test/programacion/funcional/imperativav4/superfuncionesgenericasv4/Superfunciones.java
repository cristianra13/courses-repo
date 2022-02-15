package test.programacion.funcional.imperativav4.superfuncionesgenericasv4;


import test.programacion.funcional.imperativav4.superfuncionesgenericasv4.interfaces.*;
import test.programacion.funcional.imperativav4.superfuncionesgenericasv4.interfaces.OperadorBinario;

import java.util.ArrayList;
import java.util.List;

public class Superfunciones
{

    public static <T> List<T> proveer(int size, Proveedor<T> proveedor)
    {
        List<T> lista = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            lista.add(proveedor.obtener());
        }

        return lista;
    }

    public static <T> List<T> filtrar(List<T> valores,  Predicado<T> predicado)
    {
        List<T>  resultado = new ArrayList<>();
        for(T var : valores)
        {
            if(predicado.test(var))
            {
                resultado.add(var);
            }
        }
        return resultado;
    }

    public static <T, R> List<R> transformar(List<T> valores, Funcion<T, R> funcion)
    {
        List<R> lista = new ArrayList<>();
        for(T val : valores)
        {
            lista.add(funcion.aplicar(val));
        }

        return lista;
    }

    public static <T> List<T> actuar(List<T> valores, Consumidor<T> consumida)
    {
        for(T valor : valores)
        {
            consumida.aceptar(valor);
        }
        return  valores;
    }

    public static <T> void consumir(List<T> valores, Consumidor<T> consumidor)
    {
        for(T valor : valores)
        {
            consumidor.aceptar(valor);
        }
    }

    public static <T> T reducir(List<T> valores, T identidad, OperadorBinario<T> funcionBinaria)
    {
        T total = identidad;
        for(T valor : valores)
        {
            total = funcionBinaria.aplicar(total, valor);
        }
        return total;
    }
}
