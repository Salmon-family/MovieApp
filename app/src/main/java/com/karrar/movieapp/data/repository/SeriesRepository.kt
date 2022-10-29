package com.karrar.movieapp.data.repository

import androidx.paging.PagingData
import androidx.paging.Pager
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.movie.AdventureMovieEntity
import com.karrar.movieapp.data.local.database.entity.series.AiringTodaySeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.OnTheAirSeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getTVShowsGenreList(): List<Genre>

    suspend fun getTVShowsGenreList2(): List<GenreDto>?

    suspend fun getOnTheAir(page: Int = 1): List<TVShowsDTO>

    suspend fun getAiringToday(page: Int = 1): List<TVShowsDTO>

    suspend fun getTopRatedTvShow(page: Int = 1): List<TVShowsDTO>

    suspend fun getPopularTvShow(page: Int = 1): List<TVShowsDTO>

    suspend fun getTvShowDetails(tvShowId: Int): TvShowDetails

    suspend fun getTvShowCast(tvShowId: Int): List<Actor>

    suspend fun getTvShowReviews(tvShowId: Int): List<Review>

    suspend fun setRating(tvShowId: Int, value: Float, sessionId: String): RatingDto

    suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<Rated>

    suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): Season

    suspend fun getTvShowTrailer(tvShowId: Int): Trailer

    suspend fun insertTvShow(tvShow: WatchHistoryEntity)

    suspend fun getAllTVShows(): Pager<Int,TVShowsDTO>

    suspend fun getTVShowByGenre(genreID: Int): Pager<Int, TVShowsDTO>

    fun getAiringToday(): Flow<List<AiringTodaySeriesEntity>>

    suspend fun insertAiringToday(items : List<AiringTodaySeriesEntity>)

    suspend fun deleteAiringToday()

    fun getOnTheAir(): Flow<List<OnTheAirSeriesEntity>>

    suspend fun insertOnTheAir(items : List<OnTheAirSeriesEntity>)

    suspend fun deleteOnTheAir()

    fun getTopRatedTvShow(): Flow<List<TopRatedSeriesEntity>>

    suspend fun insertTopRatedTvShow(items : List<TopRatedSeriesEntity>)

    suspend fun deleteTopRatedTvShow()

    fun getAiringTodayTvShowPager(): Pager<Int, TVShowsDTO>

    fun getOnTheAirTvShowPager(): Pager<Int, TVShowsDTO>

    fun getTopRatedTvShowPager(): Pager<Int, TVShowsDTO>

    fun getPopularTvShowPager(): Pager<Int, TVShowsDTO>
}