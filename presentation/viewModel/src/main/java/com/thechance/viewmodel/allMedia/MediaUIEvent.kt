package com.thechance.viewmodel.allMedia

sealed interface MediaUIEvent {
    object BackEvent : MediaUIEvent
    object RetryEvent : MediaUIEvent
    data class ClickMovieEvent(val movieID: Int) : MediaUIEvent
    data class ClickSeriesEvent(val seriesID: Int) : MediaUIEvent
}