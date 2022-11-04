package com.thechance.movie.ui.home.homeUiState

sealed interface HomeUIEvent {
    object ClickSeeAllActorEvent : HomeUIEvent
    data class ClickMovieEvent(val movieID: Int) : HomeUIEvent
    data class ClickActorEvent(val actorID: Int) : HomeUIEvent
    data class ClickSeriesEvent(val seriesID: Int) : HomeUIEvent
    data class ClickSeeAllMovieEvent(val mediaType: com.devfalah.types.AllMediaType) : HomeUIEvent
    data class ClickSeeAllTVShowsEvent(val mediaType: com.devfalah.types.AllMediaType) : HomeUIEvent
}