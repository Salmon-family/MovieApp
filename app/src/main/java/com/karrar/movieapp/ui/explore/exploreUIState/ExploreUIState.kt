package com.karrar.movieapp.ui.explore.exploreUIState


data class ExploreUIState(
    val trendyMovie: List<TrendyMediaUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: List<ErrorUIState> = emptyList()
)
