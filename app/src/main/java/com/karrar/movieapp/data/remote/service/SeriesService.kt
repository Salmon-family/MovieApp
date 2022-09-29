package com.karrar.movieapp.data.remote.service

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Series
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface SeriesService {
    @GET("tv/on_the_air")
    suspend fun getOnTheAir(): Response<BaseResponse<MovieDto>>

    @GET("tv/airing_today")
    suspend fun getAiringToday(): Response<BaseResponse<MovieDto>>

}