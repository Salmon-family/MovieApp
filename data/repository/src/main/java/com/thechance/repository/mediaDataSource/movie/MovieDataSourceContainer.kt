package com.thechance.repository.mediaDataSource.movie

import javax.inject.Inject

class MovieDataSourceContainer @Inject constructor(
    val nowStreamingMovieMovieDataSource: NowStreamingMovieDataSource,
    val trendingMovieDataSource: TrendingMovieDataSource,
    val upcomingMovieMovieDataSource: UpcomingMovieDataSource,
    val movieByGenreDataSource: MovieByGenreDataSource
)