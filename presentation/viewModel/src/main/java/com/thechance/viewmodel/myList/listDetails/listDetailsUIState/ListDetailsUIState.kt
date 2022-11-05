package com.thechance.viewmodel.myList.listDetails.listDetailsUIState

import com.thechance.viewmodel.category.uiState.ErrorUIState


data class ListDetailsUIState(
    val savedMedia: List<SavedMediaUIState> = emptyList(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: List<ErrorUIState> = emptyList()
)