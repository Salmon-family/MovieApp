package com.thechance.viewmodel.movieDetails.saveMovie.uiState

import com.thechance.viewmodel.category.uiState.ErrorUIState

data class MySavedListUIState(
    val myListItemUI: List<MyListItemUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: List<ErrorUIState> = emptyList()
)

