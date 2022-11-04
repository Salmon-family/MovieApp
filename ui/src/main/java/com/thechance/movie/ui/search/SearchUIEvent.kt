package com.thechance.movie.ui.search

import com.thechance.movie.ui.search.mediaSearchUIState.MediaUIState

sealed interface SearchUIEvent {
    data class ClickMediaEvent(val mediaUIState: MediaUIState) : SearchUIEvent
    data class ClickActorEvent(val actorID: Int) : SearchUIEvent
    object ClickBackEvent : SearchUIEvent
    object ClickRetryEvent : SearchUIEvent
}
