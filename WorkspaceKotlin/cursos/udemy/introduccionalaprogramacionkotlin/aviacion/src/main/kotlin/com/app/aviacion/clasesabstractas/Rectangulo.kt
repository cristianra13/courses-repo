package com.app.aviacion.clasesabstractas

import kotlin.math.roundToInt

class Rectangulo(var base: Double, var altura: Double) : Poligono() {

    override fun getArea(): Double {
        return base * altura
    }

    override fun getPerimetro(): Double {
        return (2 * base) + (2 * altura)
    }

    override fun dibujar() {
        for (i in 1..altura.roundToInt()) {
            println(" * ".repeat(base.toInt()))
        }
    }

    fun getAreaEnPulgadas(): Double {
        return centimetrosAPulgadas(getArea())
    }

    fun getPerimetroEnPulgadas(): Double {
        return centimetrosAPulgadas(getPerimetro())
    }
}