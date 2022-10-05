package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<State<List<PopularMovie>>>

    fun getUpcomingMovies(): Flow<State<List<Media>>>

    fun getTopRatedMovies(): Flow<State<List<Media>>>

    fun getNowPlayingMovies(): Flow<State<List<Media>>>

    fun getTrendingMovies(): Flow<State<List<Media>>>

    fun getTrendingPersons(): Flow<State<List<Actor>>>

    fun getGenreList(): Flow<State<List<Genre>>>

    fun getMovieListByGenre(genreID: Int): Flow<State<List<Media>>>
    fun getActorDetails(actorId: Int): Flow<State<ActorDetails>>

    fun getTrendingActors(): Flow<State<List<Actor>>>

    fun getActorMovies(actorId: Int): Flow<State<List<Media>>>

    fun getAccountDetails(sessionId: String?): Flow<State<Account>>

    fun getRatedMovies(sessionId: String?): Flow<State<List<RatedMovie>>>
}