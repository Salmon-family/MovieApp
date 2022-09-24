package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface  MovieRepository {

     fun getPopularMovies() : Flow<State<MovieResponse>>

     fun getUpcomingMovies() : Flow<State<MovieResponse>>

     fun getTopRatedMovies() :  Flow<State<MovieResponse>>

     fun getNowPlayingMovies() :  Flow<State<MovieResponse>>
}