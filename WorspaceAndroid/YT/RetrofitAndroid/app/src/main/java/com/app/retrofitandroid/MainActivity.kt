package com.app.retrofitandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.retrofitandroid.adapters.DogAdapter
import com.app.retrofitandroid.databinding.ActivityMainBinding
import com.app.retrofitandroid.service.ApiDogService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svBuscar.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    // creamos una instancia de Retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        // Creamos una corrutina
        CoroutineScope(Dispatchers.IO).launch {
            // Todo lo que se haga acá, se hace en un hilo secundario
            val call = getRetrofit().create(ApiDogService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body() // obtenemos el body
            runOnUiThread{
                // Todo esto se ejecuta en el hilo principal
                if (call.isSuccessful) {
                    // Show recycler view
                    val images: List<String> = puppies?.imagenes ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    // Avisamos al adapter de los cambios
                    adapter.notifyDataSetChanged()

                } else {
                    // Show error
                    showError()
                }
                hideKeyboard()
            }
        }
    }

    // Oculta el teclado cada vez que se busque algo
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    // Estos métodos nos avisan cuando el usuario escriba una letra o borre, cuando haya un cambio
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}