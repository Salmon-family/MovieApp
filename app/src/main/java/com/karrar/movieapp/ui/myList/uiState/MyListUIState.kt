package com.karrar.movieapp.ui.myList.uiState

data class MyListUIState(
    val createdList: List<CreatedListUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)