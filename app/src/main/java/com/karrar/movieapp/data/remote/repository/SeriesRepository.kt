package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    fun getOnTheAir(): Flow<State<List<Media>>>

    fun getAiringToday(): Flow<State<List<Media>>>

    fun getTopRatedTvShow(): Flow<State<List<Media>>>

    fun getPopularTvShow(): Flow<State<List<Media>>>

    fun getLatestTvShow(): Flow<State<List<Media>>>
}