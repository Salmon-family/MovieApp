package com.karrar.movieapp.ui.profile.myratings

sealed interface MyRatingUIEvent {
    data class MovieEvent(val movieID: Int) : MyRatingUIEvent
    data class TVShowEvent(val tvShowID: Int) : MyRatingUIEvent
}