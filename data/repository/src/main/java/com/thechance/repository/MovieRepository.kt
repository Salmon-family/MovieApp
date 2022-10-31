package com.thechance.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.thechance.repository.local.database.entity.ActorEntity
import com.thechance.repository.local.database.entity.SearchHistoryEntity
import com.thechance.repository.local.database.entity.WatchHistoryEntity
import com.thechance.repository.local.database.entity.movie.*
import com.thechance.repository.remote.response.*
import com.thechance.repository.remote.response.actor.ActorDto
import com.thechance.repository.remote.response.actor.ActorMoviesDto
import com.thechance.repository.remote.response.genre.GenreDto
import com.thechance.repository.remote.response.movie.MovieDetailsDto
import com.thechance.repository.remote.response.movie.RatingDto
import com.thechance.repository.remote.response.review.ReviewsDto
import com.thechance.repository.remote.response.trailerVideosDto.TrailerDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieGenreList(): List<GenreDto>?

    suspend fun getDailyTrending(): BaseListResponse<DailyTrendingDto>


    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto?

    suspend fun getMovieCast(movieId: Int): List<ActorDto>?

    suspend fun getSimilarMovie(movieId: Int): List<MovieDto>?

    suspend fun getMovieReviews(movieId: Int): List<ReviewsDto>?

    suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto

    suspend fun getMovieTrailer(movieId: Int): TrailerDto?

    suspend fun getRatedMovie(accountId: Int, sessionId: String): List<RatedMoviesDto>?


    suspend fun getActorDetails(actorId: Int): ActorDto?

    suspend fun getActorMovies(actorId: Int): ActorMoviesDto?


    suspend fun getAllLists(sessionId: String): List<CreatedListDto>?

    suspend fun getListDetails(listId: Int): MyListsDto?

    suspend fun getSavedListDetails(listId: Int): List<SavedListDto>?

    suspend fun createList(sessionId: String, name: String): AddListResponse?

    suspend fun addMovieToList(sessionId: String, listId: Int, movieId: Int): AddMovieDto

//    fun searchForMovie(query: String): Flow<PagingData<Media>>
//
//    fun searchForSeries(query: String): Flow<PagingData<Media>>
//
//    fun searchForActor(query: String): Flow<PagingData<Media>>
//
//    fun getAllSearchHistory(): Flow<List<SearchHistory>>

    suspend fun clearWatchHistory()

    suspend fun insertSearchItem(item: SearchHistoryEntity)

    suspend fun deleteSearchItem(item: SearchHistoryEntity)

    suspend fun insertMovie(movie: WatchHistoryEntity)

    fun getAllWatchedMovies(): Flow<List<WatchHistoryEntity>>

    suspend fun getActorData(): Pager<Int, ActorDto>

    suspend fun getAllMovies(): Pager<Int, MovieDto>

    suspend fun getMovieByGenre(genreID: Int): Pager<Int, MovieDto>

    fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    suspend fun insertPopularMovies(items: List<PopularMovieEntity>)

    suspend fun deletePopularMovies()

    fun getTrendingMovies(): Flow<List<TrendingMovieEntity>>

    suspend fun insertTrendingMovies(items: List<TrendingMovieEntity>)

    suspend fun deleteTrendingMovies()

    fun getNowStreamingMovies(): Flow<List<NowStreamingMovieEntity>>

    suspend fun insertNowStreamingMovies(items: List<NowStreamingMovieEntity>)

    suspend fun deleteNowStreamingMovies()

    fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>>

    suspend fun insertUpcomingMovies(items: List<UpcomingMovieEntity>)

    suspend fun deleteUpcomingMovies()

    fun getAdventureMovies(): Flow<List<AdventureMovieEntity>>

    suspend fun insertAdventureMovies(items: List<AdventureMovieEntity>)

    suspend fun deleteAdventureMovies()

    fun getMysteryMovies(): Flow<List<MysteryMovieEntity>>

    suspend fun insertMysteryMovies(items: List<MysteryMovieEntity>)

    suspend fun deleteMysteryMovies()

    fun getTrendingActors(): Flow<List<ActorEntity>>

    suspend fun insertTrendingActors(items: List<ActorEntity>)

    suspend fun deleteTrendingActors()

    suspend fun saveRequestDate(value: Long)

    suspend fun getRequestDate(): Long?

    suspend fun getPopularMovies(page: Int = 1): List<MovieDto>

    suspend fun getTrendingMovies(page: Int = 1): List<MovieDto>


    suspend fun getNowStreamingMovies(page: Int = 1): List<MovieDto>

    suspend fun getAdventureMovies(page: Int = 1): List<MovieDto>

    suspend fun getUpcomingMovies(page: Int = 1): List<MovieDto>

    suspend fun getMysteryMovies(page: Int = 1): List<MovieDto>

    suspend fun getTrendingActors(page: Int = 1): List<ActorDto>

    suspend fun getTrendingMoviesPager(): Pager<Int, MovieDto>

    suspend fun getNowPlayingMoviesPager(): Pager<Int, MovieDto>

    suspend fun getUpcomingMoviesPager(): Pager<Int, MovieDto>

    suspend fun getActorMoviesPager(actorId: Int): Pager<Int, MovieDto>

    suspend fun getAdventureMoviesPager(): Pager<Int, MovieDto>

    suspend fun getMysteryMoviesPager(): Pager<Int, MovieDto>


}