package com.karrar.movieapp.data.local.mappers.series

import javax.inject.Inject

class LocalSeriesMappersContainer @Inject constructor(
    val topRatedSeriesMapper: TopRatedSeriesMapper,
    val onTheAirSeriesMapper: OnTheAirSeriesMapper,
    val airingTodaySeriesMapper: AiringTodaySeriesMapper,
    )