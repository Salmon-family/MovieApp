package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies2(genres: List<Genre>): List<PopularMovie>

    fun getUpcomingMovies(): Flow<State<List<Media>>>

    suspend fun getUpcomingMovies2(): List<Media>

    fun getTopRatedMovies(): Flow<State<List<Media>>>

    fun getNowPlayingMovies(): Flow<State<List<Media>>>

    suspend fun getNowPlayingMovies2(): List<Media>

    fun getTrendingMovies(): Flow<State<List<Media>>>

    suspend fun getTrendingMovies2(): List<Media>

    suspend fun searchForActor(query: String): List<Media>

    suspend fun searchForMovie(query: String): List<Media>

    fun getMovieGenreList(): Flow<State<List<Genre>>>

    suspend fun getMovieGenreList2(): List<Genre>

    fun getMovieListByGenreID(genreID: Int): Flow<State<List<Media>>>

    suspend fun getMovieListByGenreID2(genreID: Int): List<Media>

    suspend fun getActorDetails(actorId: Int): ActorDetails

    suspend fun insertSearchItem(item: SearchHistoryEntity)

    suspend fun deleteSearchItem(item: SearchHistoryEntity)

    fun getAllSearchHistory(): Flow<List<SearchHistory>>

    suspend fun getTrendingActors(): List<Actor>

    suspend fun getActorMovies(actorId: Int): List<Media>

    suspend fun getDailyTrending(): List<Media>

    suspend fun getAllMovies(): List<Media>

   suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMovieCast(movieId: Int): List<Actor>

    suspend fun getSimilarMovie(movieId: Int): List<Media>

    suspend fun getMovieReviews(movieId: Int): List<Review>

    fun setRating(movieId: Int, value:Float, session_id: String): Flow<State<RatingDto>>

    suspend fun getMovieTrailer(movieId: Int): Flow<State<Trailer>>

    fun getAllLists(accountId: Int, sessionId:String): Flow<State<List<CreatedList>>>

    fun addMovieToList(sessionId: String, listId: Int, movieId: Int): Flow<State<AddMovieDto>>

    fun getListDetails(listId: Int): Flow<State<ListDetailsDto>>

    suspend fun getRatedMovie(accountId: Int, sessionId: String): List<RatedMovies>

    suspend fun insertMovie(movie: WatchHistoryEntity)

    fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>>

    suspend fun clearWatchHistory()

    fun createList(sessionId: String, name: String): Flow<State<AddListResponse>>
}