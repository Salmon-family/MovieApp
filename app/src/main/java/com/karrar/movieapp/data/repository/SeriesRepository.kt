package com.karrar.movieapp.data.repository

import androidx.paging.PagingData
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.TVShowsDTO
import com.karrar.movieapp.data.remote.response.genre.GenreDto
import com.karrar.movieapp.data.remote.response.genre.GenreResponse
import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.models.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SeriesRepository {

    suspend fun getTVShowsGenreList(): List<Genre>

    suspend fun getTVShowsGenreList2(): Response<GenreResponse>

    suspend fun getOnTheAir(page: Int = 1): List<Media>

    suspend fun getAiringToday(page: Int = 1): List<Media>

    suspend fun getTopRatedTvShow(page: Int = 1): List<Media>

    suspend fun getPopularTvShow(page: Int = 1): List<Media>

    suspend fun getTvShowDetails(tvShowId: Int): TvShowDetails

    suspend fun getTvShowCast(tvShowId: Int): List<Actor>

    suspend fun getTvShowReviews(tvShowId: Int): List<Review>

    suspend fun setRating(tvShowId: Int, value: Float, sessionId: String): RatingDto

    suspend fun getRatedTvShow(accountId: Int, sessionId: String): List<Rated>

    suspend fun getSeasonDetails(tvShowId: Int, seasonId: Int): Season

    suspend fun getTvShowTrailer(tvShowId: Int): Trailer

    suspend fun insertTvShow(tvShow: WatchHistoryEntity)

    fun getAllTVShows(): Flow<PagingData<TVShowsDTO>>

    fun getTVShowByGenre(genreID: Int): Flow<PagingData<TVShowsDTO>>

    fun getAiringToday(): Flow<List<Media>>

    fun getOnTheAir(): Flow<List<Media>>

    fun getTopRatedTvShow(): Flow<List<Media>>

    suspend fun refreshTopRatedTvShow()

    suspend fun refreshAiringToday()

    suspend fun refreshOnTheAir()
}