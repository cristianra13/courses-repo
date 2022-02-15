package com.app.retrofitandroid.service

import com.app.retrofitandroid.models.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/*
Interface para acceder a nuestro servicio
 */
interface ApiDogService {

    //Tipo de llamada
    @GET
    suspend fun getDogsByBreeds(@Url url: String): Response<DogResponse>

}