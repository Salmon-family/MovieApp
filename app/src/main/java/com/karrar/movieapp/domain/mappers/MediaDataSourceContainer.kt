package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.repository.mediaDataSource.*
import javax.inject.Inject

class MediaDataSourceContainer @Inject constructor(
    val trendingMovieDataSource: TrendingMovieDataSource,
    val upcomingMovieMovieDataSource: UpcomingMovieMovieDataSource,
    val nowStreamingMovieMovieDataSource: NowStreamingMovieMovieDataSource,
    val airingTodayTvShowDataSource: AiringTodayTvShowDataSource,
    val popularTvShowDataSource: PopularTvShowDataSource,
    val topRatedTvShowDataSource: TopRatedTvShowDataSource,
    val onTheAirTvShowDataSource: OnTheAirTvShowDataSource,
    val actorMovieDataSource: ActorMovieDataSource,

    val movieByGenreDataSource: MovieByGenreDataSource,
    val tvShowsByGenreDataSource: TVShowsByGenreDataSource,
    val movieDataSource: MovieDataSource,
    val tvShowDataSource: TVShowDataSource

)