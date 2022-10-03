package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.Person
import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.actorDetailsDto.CastDto
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getUpcomingMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getTopRatedMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getNowPlayingMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getTrendingMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getTrendingPersons(): Flow<State<BaseResponse<PersonDto>>>

    fun searchForMedia(type: String, query: String): Flow<State<List<Media>>>

    fun searchForPerson(query: String): Flow<State<List<Person>>>

    fun getActorDetails(actorId: Int): Flow<State<ActorDetails>>

    fun getMovieDetails(actorId: Int): Flow<State<List<Movie>>>
}