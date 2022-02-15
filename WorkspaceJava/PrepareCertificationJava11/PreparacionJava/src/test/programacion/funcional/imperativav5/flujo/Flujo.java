package test.programacion.funcional.imperativav5.flujo;


import test.programacion.funcional.imperativav5.flujo.interfaces.*;

import java.util.ArrayList;
import java.util.List;

// trabajamos con lista de datos tipo T (generica)
public class Flujo<T>
{
    private final List<T> valores;

    public Flujo(List<T> valores) {
        this.valores = valores;
    }


    public static <T> Flujo<T> proveer(int size, Proveedor<T> proveedor)
    {
        List<T> lista = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            lista.add(proveedor.obtener());
        }

        return new Flujo<>(lista);
    }

    public Flujo<T> filtrar(Predicado<T> predicado)
    {
        List<T>  resultado = new ArrayList<>();
        for(T var : valores)
        {
            if(predicado.test(var))
            {
                resultado.add(var);
            }
        }
        return new Flujo<>(resultado);
    }

    public <R> Flujo<R> transformar(Funcion<T, R> funcion)
    {
        List<R> lista = new ArrayList<>();
        for(T val : valores)
        {
            lista.add(funcion.aplicar(val));
        }

        return new Flujo<>(lista);
    }

    public Flujo<T> actuar(Consumidor<T> consumida)
    {
        for(T valor : valores)
        {
            consumida.aceptar(valor);
        }
        return  new Flujo<>(valores);
    }

    // operación terminal, ya que no se puede enganchar con otra llamada ya que es void
    public void consumir(Consumidor<T> consumidor)
    {
        for(T valor : valores)
        {
            consumidor.aceptar(valor);
        }
    }

    public T reducir(T identidad, OperadorBinario<T> funcionBinaria)
    {
        T total = identidad;
        for(T valor : valores)
        {
            total = funcionBinaria.aplicar(total, valor);
        }
        return total;
    }

    @Override
    public String toString() {
        return valores.toString();
    }
}
