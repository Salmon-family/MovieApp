package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.*
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies2(genres: List<Genre>): List<PopularMovie>

    fun getUpcomingMovies(): Flow<State<List<Media>>>

    suspend fun getUpcomingMovies2(page: Int): List<Media>

    fun getTopRatedMovies(): Flow<State<List<Media>>>

    fun getNowPlayingMovies(): Flow<State<List<Media>>>

    suspend fun getNowPlayingMovies2(page: Int): List<Media>

    fun getTrendingMovies(): Flow<State<List<Media>>>

    suspend fun getTrendingMovies2(page: Int = 1): List<Media>

    suspend fun searchForActor(query: String): List<Media>

    suspend fun searchForMovie(query: String): List<Media>

    suspend fun searchForSeries(query: String): List<Media>

    fun getMovieGenreList(): Flow<State<List<Genre>>>

    suspend fun getMovieGenreList2(): List<Genre>

    fun getMovieListByGenreID(genreID: Int): Flow<State<List<Media>>>

    suspend fun getMovieListByGenreID2(genreID: Int, page: Int): List<Media>

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

    suspend fun getMovieTrailer(movieId: Int): Trailer

    suspend fun getAllLists(accountId: Int, sessionId:String): List<CreatedList>

    fun addMovieToList(sessionId: String, listId: Int, movieId: Int): Flow<State<AddMovieDto>>

    fun getListDetails(listId: Int): Flow<State<MyListsDto>>

    suspend fun getRatedMovie(accountId: Int, sessionId: String): List<RatedMovies>

    suspend fun insertMovie(movie: WatchHistoryEntity)

    fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>>

    suspend fun clearWatchHistory()

    suspend fun getSavedListDetails(listId: String): List<SaveListDetails>

    fun createList(sessionId: String, name: String): Flow<State<AddListResponse>>

    suspend fun getActorMovies2(actorId: Int): List<Media>
}