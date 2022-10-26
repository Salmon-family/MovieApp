package com.karrar.movieapp.ui.movieDetails.saveMovie.uiState


data class MySavedListUIState (
    val myListItemUI: List<MyListItemUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

