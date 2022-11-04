package com.thechance.movie.ui.youtubePlayer

import com.thechance.movie.ui.category.uiState.ErrorUIState

data class TrailerUIState(
    val videoKey: String = "",
    val isLoading: Boolean = false,
    val error: List<ErrorUIState> = emptyList()
)