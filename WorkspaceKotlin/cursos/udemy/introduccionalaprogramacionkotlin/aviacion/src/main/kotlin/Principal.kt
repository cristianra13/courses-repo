import com.app.aviacion.Nota
import java.util.*

fun main() {
    // acá envía al contructor primario
    val nota1 = Nota("Nota 1", "Contenido 1")
    println(nota1)

    // acá envía al contructor secundario
    val nota2 = Nota("Nota 2", "Contenido 2", Date())
    println(nota2)

    // Solo creamos la instancia para imprimir
    Avion()
}

open class Aeronave(protected var cantidadPasajeros: Int) {

}

class Avion : Aeronave(50) {

    init {
        println(cantidadPasajeros)
    }
}