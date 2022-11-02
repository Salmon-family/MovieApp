package com.karrar.movieapp.ui.myList.listDetails.listDetailsUIState

sealed interface ListDetailsUIEvent {
    data class OnItemSelected(val savedMediaUIState: SavedMediaUIState) : ListDetailsUIEvent
}