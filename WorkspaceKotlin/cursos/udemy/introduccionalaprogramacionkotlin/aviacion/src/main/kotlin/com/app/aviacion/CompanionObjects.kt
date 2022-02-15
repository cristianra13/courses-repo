package com.app.aviacion

class CompanionObjects {

    companion object {
        val NUMERO_UNO = 1

        fun obtenerUnValorZ(): Int {
            return 1
        }
    }
}

fun main() {
    val instance = CompanionObjects.obtenerUnValorZ()
    CompanionObjects.NUMERO_UNO
}