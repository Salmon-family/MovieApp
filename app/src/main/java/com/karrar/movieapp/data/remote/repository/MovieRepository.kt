package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<State<List<PopularMovie>>>

    fun getUpcomingMovies(): Flow<State<List<Media>>>

    fun getTopRatedMovies(): Flow<State<List<Media>>>

    fun getNowPlayingMovies(): Flow<State<List<Media>>>

    fun getTrendingMovies(): Flow<State<List<Media>>>

    fun getGenreList(): Flow<State<List<Genre>>>

    fun getMovieListByGenre(genreID: Int): Flow<State<List<Media>>>

    fun getActorDetails(actorId: Int): Flow<State<ActorDetails>>

    fun getTrendingActors(): Flow<State<List<Actor>>>

    fun getActorMovies(actorId: Int): Flow<State<List<Media?>>>
}