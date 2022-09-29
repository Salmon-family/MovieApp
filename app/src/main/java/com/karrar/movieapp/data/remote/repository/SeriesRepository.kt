package com.karrar.movieapp.data.remote.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.domain.models.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    fun getOnTheAir(): Flow<State<List<Series>>>

    fun getAiringToday(): Flow<State<List<Series>>>

}