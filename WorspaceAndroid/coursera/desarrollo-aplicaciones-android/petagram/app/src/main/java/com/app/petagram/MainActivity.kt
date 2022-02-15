package com.app.petagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var lista: ListView = TODO()
    var swipeRefres: SwipeRefreshLayout
    var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        agregarFab()

        swipeRefres = findViewById(R.id.srlMiIndicadorRefresh)
        lista = findViewById(R.id.lvMiLista)

        // Rellenamos la lista con el adapter
        var planetas: Array<String> = resources.getStringArray(R.array.planetas)
         // lista.adapter =  ArrayAdapter<ListView>(this, android.R.layout.simple_list_item_1, planetas)
    }

    // agregamos el floating action button
    fun agregarFab() {
        val miFab = findViewById<FloatingActionButton>(R.id.fabMiFab)
        // este preparado para ejecutar algo
        // Snackbar reemplaza los Toast
        miFab.setOnClickListener { view ->
            Snackbar.make(view, R.string.mensaje_toast_fab, Snackbar.LENGTH_LONG)
                .setAction(R.string.texto_accion) {
                    Log.d("SNACKBAR", "Click en Snackbar")
                }
                .setActionTextColor(resources.getColor(R.color.white))
                .setGestureInsetBottomIgnored(true)
                .show()
        }
    }
}