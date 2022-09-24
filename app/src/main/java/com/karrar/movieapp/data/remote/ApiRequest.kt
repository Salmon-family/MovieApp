package com.karrar.movieapp.data.remote

import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.utilities.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequest {
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieService : MovieService = retrofit.create(MovieService::class.java)
}