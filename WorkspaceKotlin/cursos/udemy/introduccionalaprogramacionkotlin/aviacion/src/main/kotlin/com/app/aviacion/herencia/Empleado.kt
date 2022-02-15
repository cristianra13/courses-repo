package com.app.aviacion.herencia

import java.math.BigDecimal

class Empleado(nombre: String,
               apellidos: String,
               direccion: String,
               telefono: String?,
               fechaNacimientoTexto: String,
               var salario: BigDecimal,
               var codigoEmpleado: String) :Persona(nombre, apellidos, direccion, telefono, fechaNacimientoTexto) {



    override fun toString(): String {
        // Usamos un operador ternario donde decimos que si el telefono no es nulo, lo regrese, de lo contario regrese N/D
        return "nombre: $nombre - " +
                "apellidos: $apellidos - " +
                "dirección: $direccion - " +
                "telefono: ${ telefono ?: "N/D" } - " +
                "fecha nacimiento: $fechaNacimiento " +
                "salario: $salario " +
                "código empleado: $codigoEmpleado"
    }
}