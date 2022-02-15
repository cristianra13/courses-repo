package com.app.androidusodb.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.androidusodb.models.AlumnosContract

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, AlumnosContract.Companion.Entrada.NOMBRE_TABLA, null, AlumnosContract.VERSION) {

    companion object {
        val CREATE_ALUMNOS_TABLA = "CREATE TABLE ${AlumnosContract.Companion.Entrada.NOMBRE_TABLA} " +
                "( ${AlumnosContract.Companion.Entrada.COLUMNA_ID} TEXT PRIMARY KEY, " +
                "${AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE} TEXT )"

        val REMOVE_ALUMNOS_TABLA = "DROP TABLE IF EXISTS ${AlumnosContract.Companion.Entrada.NOMBRE_TABLA}"


    }

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(CREATE_ALUMNOS_TABLA)
    }

    override fun onUpgrade(database: SQLiteDatabase?, i: Int, i2: Int) {
        // Se va a ejecutar cada vez que necesitemos actualizar nuestra BD
        database?.execSQL(REMOVE_ALUMNOS_TABLA)
        onCreate(database)
    }

}