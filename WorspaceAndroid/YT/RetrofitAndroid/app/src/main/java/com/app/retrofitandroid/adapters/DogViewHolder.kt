package com.app.retrofitandroid.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitandroid.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

// recibe la view que vamos a pintar
class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    // esto se llama por ada celda a mostrar
    fun bind(image: String) {
        Picasso.with(binding.root.context).load(image).into(binding.ivDog)
    }
}