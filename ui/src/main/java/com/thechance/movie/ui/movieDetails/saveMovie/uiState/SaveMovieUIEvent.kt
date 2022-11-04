package com.thechance.movie.ui.movieDetails.saveMovie.uiState

sealed interface SaveMovieUIEvent {
    data class DisplayMessage(val message: String) : SaveMovieUIEvent
}