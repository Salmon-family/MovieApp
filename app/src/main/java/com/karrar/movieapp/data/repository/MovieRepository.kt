package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.response.*
import androidx.paging.Pager
import androidx.paging.PagingData
import com.karrar.movieapp.data.local.database.entity.ActorEntity
import com.karrar.movieapp.data.local.database.entity.SearchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.movie.*
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.actor.ActorDto
import com.karrar.movieapp.data.remote.response.actor.ActorMoviesDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieGenreList(): List<Genre>

    suspend fun getMovieGenreList2(): List<GenreDto>?

    suspend fun getDailyTrending(): BaseListResponse<DailyTrendingDto>

    suspend fun getNowPlayingMovies(page: Int = 1): List<Media>

    suspend fun getMovieListByGenreID(genreID: Int, page: Int = 1): List<Media>


    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMovieCast(movieId: Int): List<Actor>

    suspend fun getSimilarMovie(movieId: Int): List<Media>

    suspend fun getMovieReviews(movieId: Int): List<Review>

    suspend fun setRating(movieId: Int, value: Float, session_id: String): RatingDto

    suspend fun getMovieTrailer(movieId: Int): Trailer

    suspend fun getRatedMovie(accountId: Int, sessionId: String): List<RatedMoviesDto>?


    suspend fun getActorDetails(actorId: Int): ActorDto?

    suspend fun getActorMovies(actorId: Int): ActorMoviesDto?


    suspend fun getAllLists(sessionId: String): List<CreatedListDto>?

    suspend fun getListDetails(listId: Int): MyListsDto?

    suspend fun getSavedListDetails(listId: Int): List<SavedListDto>?

    suspend fun createList(sessionId: String, name: String): AddListResponse?

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

    suspend fun getActorData(): Pager<Int, ActorDto>

    suspend fun getAllMovies() : Pager<Int, MovieDto>

    suspend fun getMovieByGenre(genreID: Int): Pager<Int, MovieDto>

    suspend fun getPopularMovies(): Flow<List<PopularMovieEntity>>


    suspend fun getTrendingMovies(): Flow<List<TrendingMovieEntity>>



   suspend fun getNowStreamingMovies(): Flow<List<NowStreamingMovieEntity>>



    suspend fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>>


    suspend fun getAdventureMovies(): Flow<List<AdventureMovieEntity>>

    suspend fun getMysteryMovies(): Flow<List<MysteryMovieEntity>>

    suspend fun getTrendingActors(): Flow<List<ActorEntity>>


    suspend fun getTrendingMoviesPager(): Pager<Int, MovieDto>

    suspend fun getNowPlayingMoviesPager(): Pager<Int, MovieDto>

    suspend fun getUpcomingMoviesPager(): Pager<Int, MovieDto>

    suspend fun getActorMoviesPager(actorId: Int): Pager<Int, MovieDto>

    suspend fun getAdventureMoviesPager(): Pager<Int, MovieDto>

    suspend fun getMysteryMoviesPager(): Pager<Int, MovieDto>


}