package com.thechance.viewmodel.myList.listDetails.listDetailsUIState

sealed interface ListDetailsUIEvent {
    data class OnItemSelected(val savedMediaUIState: SavedMediaUIState) : ListDetailsUIEvent
}