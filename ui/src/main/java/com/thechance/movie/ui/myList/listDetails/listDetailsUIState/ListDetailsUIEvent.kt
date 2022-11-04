package com.thechance.movie.ui.myList.listDetails.listDetailsUIState

sealed interface ListDetailsUIEvent {
    data class OnItemSelected(val savedMediaUIState: SavedMediaUIState) : ListDetailsUIEvent
}