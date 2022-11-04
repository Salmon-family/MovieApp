package com.thechance.movie.ui.movieDetails.saveMovie.uiState

import com.thechance.movie.ui.category.uiState.ErrorUIState
import com.thechance.movie.ui.movieDetails.saveMovie.uiState.MyListItemUI

data class MySavedListUIState(
    val myListItemUI: List<MyListItemUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: List<ErrorUIState> = emptyList()
)

