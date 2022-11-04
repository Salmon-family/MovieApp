package com.thechance.movie.ui.myList.myListUIState

import com.thechance.movie.ui.category.uiState.ErrorUIState

data class CreateListDialogUIState(
    val mediaListName: String = "",
    val error: List<ErrorUIState> = emptyList()
)