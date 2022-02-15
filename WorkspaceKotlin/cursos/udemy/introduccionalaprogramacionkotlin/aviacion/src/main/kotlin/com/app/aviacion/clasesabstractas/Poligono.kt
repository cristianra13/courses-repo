package com.app.aviacion.clasesabstractas

abstract class Poligono {

    abstract fun getArea(): Double
    abstract fun getPerimetro(): Double
    abstract fun dibujar()

    protected fun centimetrosAPulgadas(valor: Double): Double {
        return (1 / 2.54) * valor
    }
}