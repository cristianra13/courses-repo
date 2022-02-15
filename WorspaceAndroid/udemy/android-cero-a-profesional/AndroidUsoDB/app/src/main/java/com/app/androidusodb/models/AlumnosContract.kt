package com.app.androidusodb.models

import android.provider.BaseColumns

class AlumnosContract {

    companion object {
        // base columns nos permite mapear las variables con columnas
        val VERSION = 1
        class Entrada: BaseColumns {
            companion object {
                val NOMBRE_TABLA = "alumnos"
                val COLUMNA_ID = "id"
                val COLUMNA_NOMBRE = "nombre"
            }
        }


    }
}