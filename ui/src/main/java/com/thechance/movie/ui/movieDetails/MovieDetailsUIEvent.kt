package com.thechance.movie.ui.movieDetails

sealed interface MovieDetailsUIEvent {
    object ClickBackEvent : MovieDetailsUIEvent
    object ClickPlayTrailerEvent : MovieDetailsUIEvent
    object ClickSaveEvent : MovieDetailsUIEvent
    object MessageAppear : MovieDetailsUIEvent
    object ClickReviewsEvent : MovieDetailsUIEvent
    data class ClickMovieEvent(val movieID: Int) : MovieDetailsUIEvent
    data class ClickCastEvent(val castID: Int) : MovieDetailsUIEvent

}