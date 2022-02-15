package test.programacion.funcional.v9_method_references_avanzado;

public class Descripcion
{
    private final Integer value;

    public Descripcion(Integer value)
    {
        this.value = value;
    }

    public Integer getValue()
    {
        return value;
    }

    @Override
    public String toString() {
        return "V(" + value + ")";
    }
}
