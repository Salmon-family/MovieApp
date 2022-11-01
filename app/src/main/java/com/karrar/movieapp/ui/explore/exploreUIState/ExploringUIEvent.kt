package com.karrar.movieapp.ui.explore.exploreUIState

sealed class ExploringUIEvent {
    object SearchEvent : ExploringUIEvent()
    object MoviesEvent : ExploringUIEvent()
    object TVShowEvent : ExploringUIEvent()
    object ActorsEvent : ExploringUIEvent()
    data class TrendEvent(val trendyMediaUIState: TrendyMediaUIState) : ExploringUIEvent()
}