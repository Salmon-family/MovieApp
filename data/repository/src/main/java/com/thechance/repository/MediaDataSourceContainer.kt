package com.thechance.repository

import com.thechance.repository.mediaDataSource.movie.MovieByGenreDataSource
import com.thechance.repository.mediaDataSource.movie.MovieDataSource
import com.thechance.repository.mediaDataSource.series.TVShowDataSource
import com.thechance.repository.mediaDataSource.series.TVShowsByGenreDataSource
import javax.inject.Inject

class MediaDataSourceContainer @Inject constructor(
    val movieByGenreDataSource: MovieByGenreDataSource,
    val tvShowsByGenreDataSource: TVShowsByGenreDataSource,
    val movieDataSource: MovieDataSource,
    val tvShowDataSource: TVShowDataSource
)