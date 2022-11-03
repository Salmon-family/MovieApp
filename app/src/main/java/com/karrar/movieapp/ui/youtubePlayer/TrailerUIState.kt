package com.karrar.movieapp.ui.youtubePlayer

import com.karrar.movieapp.ui.category.uiState.ErrorUIState

data class TrailerUIState(
    val videoKey: String = "",
    val isLoading: Boolean = false,
    val error: List<ErrorUIState> = emptyList()
)