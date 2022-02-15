package test.programacion.funcional.v10_optional;

import java.util.*;
import java.util.function.*;

// trabajamos con lista de datos tipo T (generica)
public class Flujo<T>
{
    private final List<T> valores;

    public Flujo(List<T> valores) {
        this.valores = valores;
    }


    /**
     *
     *
     * @param size
     * @param proveedor
     * @param <T>
     * @return
     */
    public static <T> Flujo<T> proveer(int size, Supplier<T> proveedor)
    {
        List<T> lista = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            lista.add(proveedor.get());
        }

        return new Flujo<>(lista);
    }

    public Flujo<T> filtrar(Predicate<T> predicado)
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

    public <R> Flujo<R> transformar(Function<T, R> funcion)
    {
        List<R> lista = new ArrayList<>();
        for(T val : valores)
        {
            lista.add(funcion.apply(val));
        }

        return new Flujo<>(lista);
    }

    public Flujo<T> actuar(Consumer<T> consumida)
    {
        for(T valor : valores)
        {
            consumida.accept(valor);
        }
        return  new Flujo<>(valores);
    }

    // operación terminal, ya que no se puede enganchar con otra llamada ya que es void
    public void consumir(Consumer<T> consumidor)
    {
        for(T valor : valores)
        {
            consumidor.accept(valor);
        }
    }

    public Flujo<T> ordenar(Comparator<T> comparador)
    {
        List<T> listaOrdenada = new ArrayList<>(valores);
        listaOrdenada.sort(comparador);
        return new Flujo<>(listaOrdenada);
    }

    public Optional<T> max(Comparator<T> comparador)
    {
        try
        {
            return Optional.of(Collections.max(valores, comparador));
        } catch (Exception e)
        {
            // retornar un valor que represente que no hay ningun valor mayor o un maximo
            return Optional.empty();
        }
    }

    public T reducir(T identidad, BinaryOperator<T> funcionBinaria)
    {
        T total = identidad;
        for(T valor : valores)
        {
            total = funcionBinaria.apply(total, valor);
        }
        return total;
    }

    @Override
    public String toString() {
        return valores.toString();
    }
}
