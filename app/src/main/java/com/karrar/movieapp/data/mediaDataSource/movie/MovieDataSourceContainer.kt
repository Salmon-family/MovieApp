package com.karrar.movieapp.data.mediaDataSource.movie

import javax.inject.Inject

class MovieDataSourceContainer @Inject constructor(
    val movieGenreShowDataSource: MovieGenreShowDataSource,
    val nowStreamingMovieMovieDataSource: NowStreamingMovieDataSource,
    val trendingMovieDataSource: TrendingMovieDataSource,
    val upcomingMovieMovieDataSource: UpcomingMovieDataSource,
)