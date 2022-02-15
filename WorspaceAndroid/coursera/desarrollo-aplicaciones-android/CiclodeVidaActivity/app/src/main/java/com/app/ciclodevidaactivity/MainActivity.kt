package com.app.ciclodevidaactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, resources.getString(R.string.onCreate), Toast.LENGTH_SHORT).show()
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, resources.getString(R.string.onStart), Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, resources.getString(R.string.onResume), Toast.LENGTH_SHORT).show()
    }

    // Hasta este momento la actividad está en estado running

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, resources.getString(R.string.onRestart), Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, resources.getString(R.string.onPause), Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, resources.getString(R.string.onStop), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, resources.getString(R.string.onDestroy), Toast.LENGTH_SHORT).show()
    }
}
