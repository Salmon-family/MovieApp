package com.karrar.movieapp.data.remote.service

import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.MovieDto
import retrofit2.Response
import retrofit2.http.GET

interface SeriesService {
    @GET("tv/on_the_air")
    suspend fun getOnTheAir(): Response<BaseResponse<MovieDto>>

    @GET("tv/airing_today")
    suspend fun getAiringToday(): Response<BaseResponse<MovieDto>>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShow():Response<BaseResponse<MovieDto>>

    @GET("tv/popular")
    suspend fun getPopularTvShow():Response<BaseResponse<MovieDto>>

    @GET("tv/latest")
    suspend fun getLatestTvShow():Response<BaseResponse<MovieDto>>
}