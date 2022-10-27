package com.karrar.movieapp.ui.movieDetails.movieDetailsUIState

data class WatchHistoryUiState(
    val id: Int = 0,
    val posterPath: String = "",
    val movieTitle: String = "",
    val voteAverage: String = "",
    val releaseDate: String = "",
    val movieDuration: Int = 0,
    val mediaType: String = ""
)