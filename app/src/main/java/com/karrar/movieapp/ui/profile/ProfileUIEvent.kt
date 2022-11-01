package com.karrar.movieapp.ui.profile

sealed class ProfileUIEvent {
    object LoginEvent : ProfileUIEvent()
    object RatedMoviesEvent : ProfileUIEvent()
    object DialogLogoutEvent : ProfileUIEvent()
    object WatchHistoryEvent : ProfileUIEvent()
}
