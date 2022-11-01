package com.karrar.movieapp.ui.profile.watchhistory

sealed class WatchHistoryUIEvent {
    data class MovieEvent(val movieID: Int) : WatchHistoryUIEvent()
    data class TVShowEvent(val tvShowID: Int) : WatchHistoryUIEvent()
}