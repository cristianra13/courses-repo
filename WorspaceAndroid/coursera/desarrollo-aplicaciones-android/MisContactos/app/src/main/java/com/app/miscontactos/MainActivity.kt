package com.app.miscontactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.miscontactos.adapters.ContactoAdapter
import com.app.miscontactos.models.Contacto

class MainActivity : AppCompatActivity() {
    var contactos: MutableList<Contacto> = ArrayList()
    private lateinit var listaContactos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaContactos = findViewById(R.id.rvContactos)

        // var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        var gridLayoutManager: GridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        listaContactos.layoutManager = gridLayoutManager
        crearListaContactos()
        inicializarAdapter()

        // generamos nuestro ListView
        /*var lvContactos: ListView = findViewById(R.id.lvContactos)
        lvContactos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresContacto)*/

        // Intent explicito
        /*
            Agregamos un listener a la lista que siempr eeste escuchando
            cuando alguien de clic o tap a la lista, en ese momento se
            dispara el método
         */
        /*lvContactos.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            // me lleva a mi pantalla detalle contacto
            var intent: Intent = Intent(this, DetalleContacto::class.java)

            intent.putExtra(resources.getString(R.string.pNombre), contactos.get(position).nombre) // Enviamos parametros
            intent.putExtra(resources.getString(R.string.pTelefono), contactos.get(position).telefono) // Enviamos parametros
            intent.putExtra(resources.getString(R.string.pEmail), contactos.get(position).email) // Enviamos parametros

            startActivity(intent) // Iniciamos el intento

            // Eliminamos la actividad anterior
            finish()
        }*/
    }

    private fun crearListaContactos() {
        // Lista de contactos
        contactos.add(Contacto("Cristian Real", "4684464654", "cristian.real@email.com", R.drawable.circle))
        contactos.add(Contacto("Maria Diaz", "456465465478", "maria.diaz@email.com", R.drawable.aliens_city))
        contactos.add(Contacto("Helen Smith", "55748312121", "helen.smith@email.com", R.drawable.astronaut))
        contactos.add(Contacto("Robert Martinez", "2312131321", "robert.martinez@email.com", R.drawable.coffee))
        contactos.add(Contacto("Cristian Real", "4684464654", "cristian.real@email.com", R.drawable.circle))
        contactos.add(Contacto("Maria Diaz", "456465465478", "maria.diaz@email.com", R.drawable.aliens_city))
        contactos.add(Contacto("Helen Smith", "55748312121", "helen.smith@email.com", R.drawable.astronaut))
        contactos.add(Contacto("Robert Martinez", "2312131321", "robert.martinez@email.com", R.drawable.coffee))
    }

    private fun inicializarAdapter() {
        var contactoAdapter: ContactoAdapter = ContactoAdapter(this, contactos)
        // Agregamos el adapter a la lista
        listaContactos.adapter = contactoAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Se elimina Main")
    }
}