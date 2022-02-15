package com.app.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        // La forma de trabajar con viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToast.setOnClickListener {
            toast()
        }
    }

    private fun toast() {
        Toast.makeText(this, "Toast!", Toast.LENGTH_SHORT).show()
    }
}