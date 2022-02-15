package com.app.retrofitandroid.models

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message") val imagenes: List<String>,
    @SerializedName("status") val status: String
    )
