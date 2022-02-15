package com.app.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nombre: String  = "Cristian"
        var apellido: String = "Real"
        var fechaNacimiento = 1993
        var fechaActual: Int = Calendar.getInstance().get(Calendar.YEAR)

        var edad = fechaActual - fechaNacimiento
        println("$nombre $apellido tiene una edad de $edad años")

        principal()
    }

    fun principal() {
        println("Hola Humano desde principal")
    }
}