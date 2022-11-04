package com.thechance.repository

import androidx.paging.Pager
import com.thechance.local.database.entity.WatchHistoryEntity
import com.thechance.local.database.entity.series.AiringTodaySeriesEntity
import com.thechance.local.database.entity.series.OnTheAirSeriesEntity
import com.thechance.local.database.entity.series.TopRatedSeriesEntity
import com.thechance.remote.response.CreditsDto
import com.thechance.remote.response.RatedTvShowDto
import com.thechance.remote.response.TVShowsDTO
import com.thechance.remote.response.episode.EpisodeDto
import com.thechance.remote.response.genre.GenreDto
import com.thechance.remote.response.movie.RatingDto
import com.thechance.remote.response.review.ReviewsDto
import com.thechance.remote.response.trailerVideosDto.TrailerDto
import com.thechance.remote.response.tvShow.TvShowDetailsDto
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getTVShowsGenreList(): List<GenreDto>?

    suspend fun getTvShowTrailer(tvShowId: Int): TrailerDto?

    suspend fun insertTvShow(tvShow: WatchHistoryEntity)

    suspend fun getAllTVShows(): Pager<Int, TVShowsDTO>

    suspend fun getTVShowByGenre(genreID: Int): Pager<Int, TVShowsDTO>

    suspend fun getAiringToday(): Flow<List<AiringTodaySeriesEntity>>

    suspend fun getOnTheAir(): Flow<List<OnTheAirSeriesEntity>>

    suspend fun getTopRatedTvShow(): Flow<List<TopRatedSeriesEntity>>

    fun getAiringTodayTvShowPager(): Pager<Int, TVShowsDTO>

    fun getOnTheAirTvShowPager(): Pager<Int, TVShowsDTO>

    fun getTopRatedTvShowPager(): Pager<Int, TVShowsDTO>

    fun getPopularTvShowPager(): Pager<Int, TVShowsDTO>

    suspend fun searchForSeriesPager(query: String): Pager<Int, TVShowsDTO>

    suspend fun getTvShowDetails(tvShowId: Int): TvShowDetailsDto?

    suspend fun getTvShowCast(tvShowId: Int): CreditsDto?

    suspend fun getTvShowReviews(tvShowId: Int): List<ReviewsDto>?

    suspend fun setRating(tvShowId: Int, value: Float): RatingDto?

    suspend fun getRatedTvShow(): List<RatedTvShowDto>?

    suspend fun getSeasonDetails(tvShowId: Int): List<EpisodeDto>?

}