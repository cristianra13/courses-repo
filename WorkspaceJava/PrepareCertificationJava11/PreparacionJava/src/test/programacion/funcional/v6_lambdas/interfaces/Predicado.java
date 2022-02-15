package test.programacion.funcional.v6_lambdas.interfaces;

@FunctionalInterface
public interface Predicado<T>
{
    boolean test(T valor);
}
