package com.thechance.viewmodel.myList.myListUIState

sealed interface MyListUIEvent {
    object CreateButtonClicked : MyListUIEvent
    object CLickAddEvent : MyListUIEvent
    data class OnSelectItem(val createdListUIState: CreatedListUIState) : MyListUIEvent
    data class DisplayError(val errorMessage: String) : MyListUIEvent
}