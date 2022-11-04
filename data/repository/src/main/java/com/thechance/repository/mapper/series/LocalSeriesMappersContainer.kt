package com.thechance.repository.mapper.series

import javax.inject.Inject

class LocalSeriesMappersContainer @Inject constructor(
    val topRatedSeriesMapper: TopRatedSeriesMapper,
    val onTheAirSeriesMapper: OnTheAirSeriesMapper,
    val airingTodaySeriesMapper: AiringTodaySeriesMapper,
)