package com.karrar.movieapp.ui.myList.myListUIState

import com.karrar.movieapp.ui.category.uiState.ErrorUIState

data class CreateListDialogUIState(
    val mediaListName: String = "",
    val error: List<ErrorUIState> = emptyList()
)