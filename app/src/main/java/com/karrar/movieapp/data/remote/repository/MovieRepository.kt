package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.actorDetailsDto.CastDto
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getUpcomingMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getTopRatedMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getNowPlayingMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getTrendingMovies(): Flow<State<BaseResponse<MovieDto>>>

    fun getTrendingPersons(): Flow<State<BaseResponse<PersonDto>>>

    fun searchForPerson(query: String): Flow<State<List<Media>>>

    fun searchForMovie(query: String): Flow<State<List<Media>>>

    fun searchForSeries(query: String): Flow<State<List<Media>>>

    fun getActorDetails(actorId: Int): Flow<State<ActorDetails>>

    fun getMovieDetails(actorId: Int): Flow<State<List<Movie>>>

    suspend fun insertSearchItem(item: SearchHistoryEntity)

    suspend fun deleteSearchItem(item: SearchHistoryEntity)

    fun getAllSearchHistory(): Flow<List<SearchHistory>>
}