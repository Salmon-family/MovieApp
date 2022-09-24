package com.karrar.movieapp.data.remote

import com.karrar.movieapp.data.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies() : Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies() : Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<MovieResponse>
}