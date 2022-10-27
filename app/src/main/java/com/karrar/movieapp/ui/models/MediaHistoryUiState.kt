package com.karrar.movieapp.domain.models

data class MediaHistoryUiState(
    val id: Int,
    var posterPath: String,
    var movieTitle: String,
    var voteAverage: String,
    var releaseDate: String,
    var movieDuration: Int,
    var mediaType: String
)