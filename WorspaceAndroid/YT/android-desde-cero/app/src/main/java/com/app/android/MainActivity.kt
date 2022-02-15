package com.app.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"

    /**
     * El orden de ejecución del ciclo de vida del activity es:
     * 1. onCreate()
     * 2. onStart()
     * 3. onResume()
     * 4. onPause()
     * 5. onStop()
     * 6. onDestroy()
     */

    // Cuando la activity se crea
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie = findViewById<MovieView>(R.id.movie)
        movie.setMovie(Movie("Avengers: End Game", "http://"))
        /*val email = findViewById<EditText>(R.id.etEmail)
        val phone = findViewById<EditText>(R.id.etPhone)
        val password = findViewById<EditText>(R.id.etPassword)

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        btnEnviar.setOnClickListener {
            // recuperamos el email del campo
            val message = "Email: ${email.text}"
            // Siempre debemos llamar al método show para que el toast se muestre por pantalla
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }*/
    }

    // Se ejecuta antes de que la activity se destruya
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    /*
        se lanzan cuando la aplicación va a segundo plano
        o hay otra activity encima
     */
    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    /*
        Esto se activa cuando hay algo que bloquea la activity, como un dialogo
     */
    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }
}