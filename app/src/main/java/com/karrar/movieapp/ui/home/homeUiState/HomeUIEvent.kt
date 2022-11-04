package com.karrar.movieapp.ui.home.homeUiState

import com.devfalah.types.AllMediaType

sealed interface HomeUIEvent {
    object ClickSeeAllActorEvent : HomeUIEvent
    data class ClickMovieEvent(val movieID: Int) : HomeUIEvent
    data class ClickActorEvent(val actorID: Int) : HomeUIEvent
    data class ClickSeriesEvent(val seriesID: Int) : HomeUIEvent
    data class ClickSeeAllMovieEvent(val mediaType: AllMediaType) : HomeUIEvent
    data class ClickSeeAllTVShowsEvent(val mediaType: AllMediaType) : HomeUIEvent
}