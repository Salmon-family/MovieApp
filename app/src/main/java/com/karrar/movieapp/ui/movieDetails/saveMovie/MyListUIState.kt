package com.karrar.movieapp.ui.movieDetails.saveMovie


data class MySavedListUIState (
    val myListItemUI: List<MyListItemUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class MyListItemUI(
    val listID: Int = 0,
    val name: String = ""
)
