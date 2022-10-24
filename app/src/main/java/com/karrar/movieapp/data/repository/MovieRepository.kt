package com.karrar.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.response.AddListResponse
import com.karrar.movieapp.data.remote.response.AddMovieDto
import com.karrar.movieapp.data.remote.response.MyListsDto
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.enums.AllMediaType
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieGenreList(): List<Genre>

    suspend fun getTrendingMovies(page: Int = 1): List<Media>

    suspend fun getDailyTrending(): List<Media>

    suspend fun getUpcomingMovies(page: Int = 1): List<Media>

    suspend fun getNowPlayingMovies(page: Int = 1): List<Media>

    suspend fun getMovieListByGenreID(genreID: Int, page: Int = 1): List<Media>


    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMovieCast(movieId: Int): List<Actor>

    suspend fun getSimilarMovie(movieId: Int): List<Media>

    suspend fun getMovieReviews(movieId: Int): List<Review>

    suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto

    suspend fun getMovieTrailer(movieId: Int): Trailer

    suspend fun getRatedMovie(accountId: Int, sessionId: String): List<Rated>

    suspend fun getTrendingActors(page: Int): List<Actor>

    suspend fun getActorDetails(actorId: Int): ActorDetails

    suspend fun getActorMovies(actorId: Int): List<Media>


    suspend fun getAllLists(accountId: Int, sessionId: String): List<CreatedList>

    suspend fun getListDetails(listId: Int): MyListsDto

    suspend fun getSavedListDetails(listId: String): List<SaveListDetails>

    suspend fun createList(sessionId: String, name: String): AddListResponse

    suspend fun addMovieToList(sessionId: String, listId: Int, movieId: Int): AddMovieDto


    fun searchForMovie(query: String): Flow<PagingData<Media>>

    fun searchForSeries(query: String): Flow<PagingData<Media>>

    fun searchForActor(query: String): Flow<PagingData<Media>>

    fun getAllSearchHistory(): Flow<List<SearchHistory>>

    suspend fun clearWatchHistory()

    suspend fun insertSearchItem(item: SearchHistoryEntity)

    suspend fun deleteSearchItem(item: SearchHistoryEntity)

    suspend fun insertMovie(movie: WatchHistoryEntity)

    fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>>

    suspend fun getMediaData(type: AllMediaType, actorId: Int): Pager<Int, Media>

    suspend fun getActorData(): Pager<Int, Actor>

    fun getAllMedia(mediaType: Int): Flow<PagingData<Media>>

    fun getMediaByGenre(genreID: Int, mediaType: Int): Flow<PagingData<Media>>


    fun getPopularMovies(): Flow<List<PopularMovie>>

    fun getTrendingMovies(): Flow<List<Media>>

    fun getNowPlayingMovies(): Flow<List<Media>>

    fun getUpcomingMovies(): Flow<List<Media>>

    fun getAdventureMovies(): Flow<List<Media>>

    fun getMysteryMovies(): Flow<List<Media>>

    fun getTrendingActors(): Flow<List<Actor>>

    suspend fun saveRequestDate(value: Long)

    suspend fun getRequestDate(): Long?

    suspend fun refreshPopularMovies()

    suspend fun refreshTrendingMovies()

    suspend fun refreshNowPlayingMovies()

    suspend fun refreshAdventureMovies()

    suspend fun refreshUpcomingMovies()

    suspend fun refreshMysteryMovies()

    suspend fun refreshTrendingActors()

}