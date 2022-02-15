package com.app.retrfit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.app.retrfit2.config.Constants
import com.app.retrfit2.config.RetrofitInstance
import com.app.retrfit2.databinding.ActivityMainBinding
import com.app.retrfit2.services.SenaSoftApiService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchSplashImage()
    }
    private fun searchSplashImage() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitInstance
                .getRetrofit(Constants.baseUrlSenaSoft)
                .create(SenaSoftApiService::class.java)
                .getSplashImage("/api/logo/1")
            val image = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    // Si funciona, obtener url del logo para llamarlo
                    println(image?.urlImage)
                    Picasso.with(binding.root.context).load(image!!.urlImage).into(binding.ivLogo)
                } else {
                    // mostrar error
                }
            }
        }
    }
}