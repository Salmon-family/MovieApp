package com.thechance.repository

import androidx.paging.Pager
import com.thechance.repository.local.database.entity.WatchHistoryEntity
import com.thechance.repository.local.database.entity.series.AiringTodaySeriesEntity
import com.thechance.repository.local.database.entity.series.OnTheAirSeriesEntity
import com.thechance.repository.local.database.entity.series.TopRatedSeriesEntity
import com.thechance.repository.remote.response.CreditsDto
import com.thechance.repository.remote.response.RatedTvShowDto
import com.thechance.repository.remote.response.SeasonDto
import com.thechance.repository.remote.response.TVShowsDTO
import com.thechance.repository.remote.response.actor.ActorDto
import com.thechance.repository.remote.response.genre.GenreDto
import com.thechance.repository.remote.response.movie.RatingDto
import com.thechance.repository.remote.response.review.ReviewsDto
import com.thechance.repository.remote.response.trailerVideosDto.TrailerDto
import com.thechance.repository.remote.response.tvShow.TvShowDetailsDto
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getTVShowsGenreList(): List<GenreDto>?

    suspend fun getOnTheAir(page: Int = 1): List<TVShowsDTO>

    suspend fun getAiringToday(page: Int = 1): List<TVShowsDTO>

    suspend fun getTopRatedTvShow(page: Int = 1): List<TVShowsDTO>

    suspend fun getPopularTvShow(page: Int = 1): List<TVShowsDTO>

    suspend fun getTvShowDetails(tvShowId: Int): TvShowDetailsDto?

    suspend fun getTvShowCast(tvShowId: Int): List<ActorDto>?

    suspend fun getTvShowReviews(tvShowId: Int): List<ReviewsDto>

    suspend fun setRating(tvShowId: Int, value: Float, sessionId: String): RatingDto?

    suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<RatedTvShowDto>?

    suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): SeasonDto?

    suspend fun getTvShowTrailer(tvShowId: Int): TrailerDto?

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