package com.app.aviacion

import com.app.aviacion.constantes.Constantes
import java.lang.RuntimeException
import java.util.*

// constructor primario
class Nota(var nombre: String, var contenido: String, var fechaCreacion: Date) {

    // Bloque de inicialización
    // Se ejecuta siempre que se cree una instancia de la clase
    init {
        numeroInstancias++

        if (nombre.length > Constantes.LONGITUD_MAXIMA_NOMBRE) {
            throw RuntimeException("Longitud demasiado larga, máximo 10 letras.")
        }
    }

    // Companion object
    companion object {
        // Variables de clase
        private var numeroInstancias = 0

        // declaramos constantes, estás se definen durante el tiempo de compilación
        // const val LONGITUD_MAXIMA_NOMBRE = 10

        // Métodos de clase
        fun mostrarInstancias() {
            println(numeroInstancias)
        }
    }

    // constructor secundario
    // this hace referencia a la clase
    constructor(nombre: String, contenido: String): this(nombre, contenido, Date()) {
        this.nombre = nombre.uppercase()
    }

    fun imprimirCantidadLetras() {
        println("El contenido tiene un total de ${contenido.length} de letras")
    }

    fun contarCantidadPalabrasContenido(): Int {
        var cantPalabras: List<String> = contenido.split(" ")
        return cantPalabras.size
    }

    fun contarCantidadPalabrasContenido(texto: String): String {
        var cantPalabras: List<String> = texto.split(" ")
        return "El contenido tiene un total de ${cantPalabras.size} palabra(s)"
    }

    override fun toString(): String {
        return "$nombre - $contenido - $fechaCreacion"
    }
}