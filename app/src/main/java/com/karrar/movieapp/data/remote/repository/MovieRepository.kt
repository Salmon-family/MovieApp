package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<State<List<PopularMovie>>>

    fun getUpcomingMovies(): Flow<State<List<Movie>>>

    fun getTopRatedMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getNowPlayingMovies(): Flow<State<List<Movie>>>

    fun getTrendingMovies(): Flow<State<List<Movie>>>

    fun getTrendingPersons(): Flow<State<List<Actor>>>
}