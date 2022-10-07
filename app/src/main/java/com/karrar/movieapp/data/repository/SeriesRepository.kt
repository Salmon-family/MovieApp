package com.karrar.movieapp.data.repository

import com.karrar.movieapp.data.remote.State
import com.karrar.movieapp.domain.models.Genre
import com.karrar.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    fun getOnTheAir(): Flow<State<List<Media>>>

    fun getAiringToday(): Flow<State<List<Media>>>

    fun getTopRatedTvShow(): Flow<State<List<Media>>>

    fun getPopularTvShow(): Flow<State<List<Media>>>

    fun getLatestTvShows(): Flow<State<List<Media>>>

    fun getTVShowsGenreList(): Flow<State<List<Genre>>>

    fun getTvShowsByGenreID(genreId: Int): Flow<State<List<Media>>>

    fun getAllTvShows(): Flow<State<List<Media>>>

}