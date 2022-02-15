import com.app.aviacion.Nota
import java.util.*

fun main() {
    val nota1 = Nota("Info Kotlin", "Kotlin es un lenguaje el cual sirve para desarrollar......", Date())
    nota1.imprimirCantidadLetras()

    val cantPalabras: Int = nota1.contarCantidadPalabrasContenido()
    println("El contenido tiene un total de ${cantPalabras} palabra(s)")

    val canPalabras: String = nota1.contarCantidadPalabrasContenido("Este es un texto de prueba para la sobrecarga de métodos")
    println(canPalabras)

}