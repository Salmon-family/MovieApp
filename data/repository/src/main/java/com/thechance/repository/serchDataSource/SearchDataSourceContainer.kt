package com.thechance.repository.serchDataSource

import javax.inject.Inject

class SearchDataSourceContainer @Inject constructor(
    val movieSearchDataSource: MovieSearchDataSource,
    val seriesSearchDataSource: SeriesSearchDataSource,
    val actorSearchDataSource: ActorSearchDataSource
)
