package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.data.remote.response.actorDetailsDto.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.actorDetailsDto.CastDto
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.models.Movie
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

    //fun getTrendingPersons(): Flow<State<List<Actor>>>

    fun searchForPerson(query: String): Flow<State<List<MediaInfo>>>

    fun searchForMovie(query: String): Flow<State<List<MediaInfo>>>

    fun searchForSeries(query: String): Flow<State<List<MediaInfo>>>

    fun getGenreList(): Flow<State<List<Genre>>>

    fun getMovieListByGenre(genreID: Int): Flow<State<List<Media>>>

    fun getActorDetails(actorId: Int): Flow<State<ActorDetails>>

    suspend fun insertSearchItem(item: SearchHistoryEntity)

    suspend fun deleteSearchItem(item: SearchHistoryEntity)

    fun getAllSearchHistory(): Flow<List<SearchHistory>>

    fun getTrendingActors(): Flow<State<List<Actor>>>

    fun getActorMovies(actorId: Int): Flow<State<List<Media?>>>

    fun getDailyTrending(): Flow<State<List<Trend>>>

    fun getAllMovies(): Flow<State<List<Media>>>
}