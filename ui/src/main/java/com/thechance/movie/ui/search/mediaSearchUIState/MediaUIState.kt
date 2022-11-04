package com.thechance.movie.ui.search.mediaSearchUIState

data class MediaUIState(
    val mediaID: Int,
    val mediaName: String,
    val mediaImage: String,
    val mediaTypes: String,
    val mediaVoteAverage: Float,
    val mediaReleaseDate: String
)