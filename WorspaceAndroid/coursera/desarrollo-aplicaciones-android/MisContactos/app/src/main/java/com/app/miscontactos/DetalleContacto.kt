package com.app.miscontactos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView

class DetalleContacto : AppCompatActivity() {

    private lateinit var tvNombre: TextView
    private lateinit var tvTelefono: TextView
    private lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contacto)

        // Obtenemos los datos del MainActivity
        var params: Bundle? = intent.extras
        var nombre = params?.getString("nombre")
        var telefono = params?.getString("telefono")
        var email = params?.getString("email")

        tvNombre = findViewById(R.id.tvNombre)
        tvTelefono = findViewById(R.id.tvtelefono)
        tvEmail = findViewById(R.id.tvEmail)

        tvNombre.text = nombre
        tvTelefono.text = telefono
        tvEmail.text = email
    }

    fun llamar(view: View) {
        // Obtenemos el número de teléfono
        val telefono = tvTelefono.text.toString()
        // Ejecutamos intent implicito, sedebe tratar el telefono como un recurso accesible
        val intent = Intent(Intent.ACTION_DIAL).apply { 
            data = Uri.parse("tel:$telefono")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun enviarEmail(view: View) {
        val email = tvEmail.text.toString()
        var emailIntent: Intent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:") // Envio de email
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email) // A quien será enviado el correo
        emailIntent.type = "message/rfc822" // Pone como chooser todas las aplicaciones que funcionan como email
        startActivity(Intent.createChooser(emailIntent, "Email"))
    }

    /*
    Nos permite tener una sola actividad en el stack,
    por lo que cuando se oprime back, se eliminara la
    actividad en la que se estaba anteriormente
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // levantar un nuevo intent
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Se elimina Detalle")
    }
}