package com.karrar.movieapp.data.repository

import androidx.paging.Pager
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.local.database.entity.movie.AdventureMovieEntity
import com.karrar.movieapp.data.local.database.entity.series.AiringTodaySeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.OnTheAirSeriesEntity
import com.karrar.movieapp.data.local.database.entity.series.TopRatedSeriesEntity
import com.karrar.movieapp.data.remote.response.CreditsDto
import com.karrar.movieapp.data.remote.response.RatedTvShowDto
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.response.episode.EpisodeDto
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.data.remote.response.trailerVideosDto.TrailerDto
import com.karrar.movieapp.data.remote.response.tvShow.TvShowDetailsDto
import com.karrar.movieapp.domain.models.*
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

    suspend fun deleteTvShowRating(tvId: Int): RatingDto?


}