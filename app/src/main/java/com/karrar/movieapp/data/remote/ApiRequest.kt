package com.karrar.movieapp.data.remote

import com.karrar.movieapp.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequest {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieService : MovieService = retrofit.create(MovieService::class.java)
}