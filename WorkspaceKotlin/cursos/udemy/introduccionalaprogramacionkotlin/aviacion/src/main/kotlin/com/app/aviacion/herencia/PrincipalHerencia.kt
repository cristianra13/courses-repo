import com.app.aviacion.herencia.Empleado
import com.app.aviacion.herencia.Persona
import java.math.BigDecimal

fun main() {
    val persona = Persona("Cristian", "Real", "direccion", null, "25/01/1983")
    println(persona.getEdad())
    println(persona)

    val empleado =
        Empleado("Cristian", "Real A", "direccion", "35646465", "25/01/1993", BigDecimal("1500000"), "abcd1234")
    println(empleado.getEdad())
    println(empleado)
}