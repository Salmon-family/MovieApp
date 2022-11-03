package com.karrar.movieapp.data.repository.mediaDataSource.series

import javax.inject.Inject

class SeriesDataSourceContainer @Inject constructor(
    val airingTodayTvShowDataSource: AiringTodayTvShowDataSource,
    val topRatedTvShowDataSource: TopRatedTvShowDataSource,
    val onTheAirTvShowDataSource: OnTheAirTvShowDataSource,
    val popularTvShowDataSource: PopularTvShowDataSource,
)