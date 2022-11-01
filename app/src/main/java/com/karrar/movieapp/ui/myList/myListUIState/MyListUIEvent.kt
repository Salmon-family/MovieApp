package com.karrar.movieapp.ui.myList.myListUIState

sealed class MyListUIEvent {
    object CreateButtonClicked : MyListUIEvent()
//    object CLickAddEvent : MyListUIEvent()
    data class OnSelectItem(val createdListUIState: CreatedListUIState) : MyListUIEvent()
    data class DisplayError(val errorMessage: String) : MyListUIEvent()
}