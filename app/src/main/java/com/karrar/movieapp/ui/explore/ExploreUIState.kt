package com.karrar.movieapp.ui.explore

data class ExploreUIState (
    val trendyMovie: List<TrendyMediaUIState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList()
)

data class TrendyMediaUIState(
    val mediaID: Int,
    val mediaType: String,
    val mediaImage: String
)

data class Error(
    val code: Int,
    val message: String
)
