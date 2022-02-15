package com.app.aviacion.herencia

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

// permitimos la herencia con open
open class Persona(
    var nombre: String,
    var apellidos: String,
    var direccion: String,
    var telefono: String?,
    var fechaNacimientoTexto: String
) {
    companion object {
        const val FORMATO_FECHA = "dd/MM/yyyy"
    }

    // Formateamos la fecha
    var fechaNacimiento: LocalDate = LocalDate.parse(fechaNacimientoTexto, DateTimeFormatter.ofPattern(FORMATO_FECHA))


    fun getEdad(): Int {
        return Period.between(fechaNacimiento, LocalDate.now()).years
    }

    override fun toString(): String {
        // Usamos un operador ternario donde decimos que si el telefono no es nulo, lo regrese, de lo contario regrese N/D
        return "nombre: $nombre - apellidos: $apellidos - dirección: $direccion - telefono: ${ telefono ?: "N/D" } - fecha nacimiento: $fechaNacimiento"
    }
}