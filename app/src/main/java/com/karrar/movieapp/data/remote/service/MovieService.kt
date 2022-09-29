package com.karrar.movieapp.data.remote.service

import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.GenreResponse
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.enums.TrendingTimeWindow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


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

    @GET("genre/movie/list")
    suspend fun getGenreList(): Response<GenreResponse>

}



