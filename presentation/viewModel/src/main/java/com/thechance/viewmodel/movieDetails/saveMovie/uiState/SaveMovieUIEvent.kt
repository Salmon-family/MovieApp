package com.thechance.viewmodel.movieDetails.saveMovie.uiState

sealed interface SaveMovieUIEvent {
    data class DisplayMessage(val message: String) : SaveMovieUIEvent
}