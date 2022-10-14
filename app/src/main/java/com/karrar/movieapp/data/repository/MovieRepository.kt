package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.response.AddListResponse
import com.karrar.movieapp.data.remote.response.AddMovieDto
import com.karrar.movieapp.data.remote.response.MyListsDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(genres: List<Genre>): List<PopularMovie>

    suspend fun getUpcomingMovies(page: Int = 1): List<Media>

    suspend fun getNowPlayingMovies(page: Int = 1): List<Media>

    suspend fun getTrendingMovies(page: Int = 1): List<Media>

    suspend fun searchForActor(query: String): List<Media>

    suspend fun searchForMovie(query: String): List<Media>

    suspend fun searchForSeries(query: String): List<Media>

    suspend fun getMovieGenreList(): List<Genre>

    suspend fun getMovieListByGenreID(genreID: Int, page: Int): List<Media>

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

    suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto

    suspend fun getMovieTrailer(movieId: Int): Trailer

    suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedList>

    suspend fun addMovieToList(sessionId: String, listId: Int, movieId: Int): AddMovieDto

    suspend fun getListDetails(listId: Int): MyListsDto

    suspend fun getRatedMovie(accountId: Int, sessionId: String): List<RatedMovies>

    suspend fun insertMovie(movie: WatchHistoryEntity)

    fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>>

    suspend fun clearWatchHistory()

    suspend fun getSavedListDetails(listId: String): List<SaveListDetails>

    suspend fun createList(sessionId: String, name: String): AddListResponse
}