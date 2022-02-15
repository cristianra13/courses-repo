package com.app.retrfit2.services

import com.app.retrfit2.models.ImagePresentationModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface SenaSoftApiService {

    @GET
    suspend fun getSplashImage(@Url url: String): Response<ImagePresentationModel>
}