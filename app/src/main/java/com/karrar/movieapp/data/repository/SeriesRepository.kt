package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    fun getOnTheAir(): Flow<State<List<Media>>>

    suspend fun getOnTheAir2(page: Int = 1): List<Media>

    fun getAiringToday(): Flow<State<List<Media>>>

    suspend fun getAiringToday2(): List<Media>

    suspend fun getTopRatedTvShow2(): List<Media>

    suspend fun getPopularTvShow(): List<Media>

    suspend fun getTVShowsGenreList(): List<Genre>

    suspend fun getTvShowsByGenreID(genreId: Int): List<Media>

    suspend fun getAllTvShows(): List<Media>

}