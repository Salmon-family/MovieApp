package com.karrar.movieapp.ui.profile.watchhistory

sealed interface WatchHistoryUIEvent {
    data class MovieEvent(val movieID: Int) : WatchHistoryUIEvent
    data class TVShowEvent(val tvShowID: Int) : WatchHistoryUIEvent
}