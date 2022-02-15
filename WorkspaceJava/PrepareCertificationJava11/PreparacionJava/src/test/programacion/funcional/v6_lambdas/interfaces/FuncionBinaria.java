package test.programacion.funcional.v6_lambdas.interfaces;

@FunctionalInterface
public interface FuncionBinaria<T, U, R>
{
    R aplicar(T valor1, U valor2);
}
