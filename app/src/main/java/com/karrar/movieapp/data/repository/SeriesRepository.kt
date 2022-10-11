package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.data.remote.response.BaseResponse
import com.karrar.movieapp.data.remote.response.movie.RatedMovie
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

    fun getTVShowsGenreList(): Flow<State<List<Genre>>>

    fun getTvShowsByGenreID(genreId: Int): Flow<State<List<Media>>>

    fun getAllTvShows(): Flow<State<List<Media>>>

    fun getTvShowDetails(tvShowId: Int): Flow<State<TvShowDetails>>

    fun getTvShowCast(tvShowId: Int): Flow<State<List<Actor>>>

    fun getTvShowReviews(tvShowId: Int): Flow<State<List<Review>>>

    fun setRating(tvShowId: Int, value: Float, sessionId: String): Flow<State<RatingDto>>

    fun getRatedTvShow(accountId: Int, sessionId: String): Flow<State<BaseResponse<RatedMovie>>>

    fun getSeasonDetails(tvShowId: Int, seasonId: Int): Flow<State<Season>>

    fun getTvShowTrailer(tvShowId: Int): Flow<State<Trailer>>

}