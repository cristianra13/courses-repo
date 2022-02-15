package com.app.retrfit2.models

import com.google.gson.annotations.SerializedName

data class ImagePresentationModel(
    @SerializedName("url_logo") val urlImage: String,
    @SerializedName("respuesta") val statusResponse: String)
