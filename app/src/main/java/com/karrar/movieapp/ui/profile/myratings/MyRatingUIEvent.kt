package com.karrar.movieapp.ui.profile.myratings

sealed class MyRatingUIEvent {
    data class MovieEvent(val movieID: Int) : MyRatingUIEvent()
    data class TVShowEvent(val tvShowID: Int) : MyRatingUIEvent()
}