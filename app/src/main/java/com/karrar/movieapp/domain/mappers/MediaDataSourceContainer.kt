package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.repository.mediaDataSource.movie.MovieByGenreDataSource
import com.karrar.movieapp.data.repository.mediaDataSource.movie.MovieDataSource
import com.karrar.movieapp.data.repository.mediaDataSource.series.TVShowDataSource
import com.karrar.movieapp.data.repository.mediaDataSource.series.TVShowsByGenreDataSource
import javax.inject.Inject

class MediaDataSourceContainer @Inject constructor(
    val movieByGenreDataSource: MovieByGenreDataSource,
    val tvShowsByGenreDataSource: TVShowsByGenreDataSource,
    val movieDataSource: MovieDataSource,
    val tvShowDataSource: TVShowDataSource
)