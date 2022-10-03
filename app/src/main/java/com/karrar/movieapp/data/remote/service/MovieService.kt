package com.karrar.movieapp.data.remote.service

import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.MediaDto
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.enums.TrendingTimeWindow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<BaseResponse<MovieDto>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<BaseResponse<MovieDto>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<BaseResponse<MovieDto>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<BaseResponse<MovieDto>>

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String = TrendingTimeWindow.DAY.value,
    ): Response<BaseResponse<MovieDto>>


    @GET("trending/person/{time_window}")
    suspend fun getTrendingPersons(
        @Path("time_window") timeWindow: String = TrendingTimeWindow.DAY.value,
    ): Response<BaseResponse<PersonDto>>

    @GET("search/{type}")
    suspend fun searchWithType(
        @Path("type") type: String,
        @Query("query") query: String,
    ): Response<BaseResponse<MediaDto>>

    @GET("search/person")
    suspend fun searchForPerson(
        @Query("query") query: String
    ): Response<BaseResponse<PersonDto>>
}



