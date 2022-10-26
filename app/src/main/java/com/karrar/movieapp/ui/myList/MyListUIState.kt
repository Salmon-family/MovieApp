package com.karrar.movieapp.ui.myList

data class MyListUIState(
    val createdList: List<CreatedListUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)