package com.thechance.viewmodel.myList.myListUIState

import com.thechance.viewmodel.category.uiState.ErrorUIState

data class CreateListDialogUIState(
    val mediaListName: String = "",
    val error: List<ErrorUIState> = emptyList()
)