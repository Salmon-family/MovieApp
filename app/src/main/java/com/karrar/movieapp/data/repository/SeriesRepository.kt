package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    fun getOnTheAir(): Flow<State<List<Media>>>

    suspend fun getOnTheAir2(): List<Media>

    fun getAiringToday(): Flow<State<List<Media>>>

    suspend fun getAiringToday2(): List<Media>

    fun getTopRatedTvShow(): Flow<State<List<Media>>>

    suspend fun getTopRatedTvShow2(): List<Media>

    fun getPopularTvShow(): Flow<State<List<Media>>>

    fun getLatestTvShows(): Flow<State<List<Media>>>

    suspend fun getTVShowsGenreList(): List<Genre>

    suspend fun getTvShowsByGenreID(genreId: Int): List<Media>

    suspend fun getAllTvShows(): List<Media>

    suspend fun getTvShowDetails(tvShowId: Int): TvShowDetails

    suspend fun getTvShowCast(tvShowId: Int): List<Actor>

    suspend fun getTvShowReviews(tvShowId: Int): List<Review>

    fun setRating(tvShowId: Int, value: Float, sessionId: String): Flow<State<RatingDto>>

    suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<RatedMovies>

    suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): Season

    suspend fun getTvShowTrailer(tvShowId: Int): Trailer

    suspend fun insertTvShow(tvShow: WatchHistoryEntity)

}