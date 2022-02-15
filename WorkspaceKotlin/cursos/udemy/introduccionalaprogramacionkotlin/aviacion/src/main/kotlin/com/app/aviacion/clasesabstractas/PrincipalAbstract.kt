import com.app.aviacion.clasesabstractas.Rectangulo

fun main() {

    val rectangulo = Rectangulo(10.0, 5.0)
    println(rectangulo.getArea())
    println(rectangulo.getPerimetro())
    println()
    println(rectangulo.getAreaEnPulgadas())
    println(rectangulo.getPerimetroEnPulgadas())
    println()
    rectangulo.dibujar()
}