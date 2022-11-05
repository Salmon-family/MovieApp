package com.thechance.viewmodel.movieDetails.movieDetailsUIState

data class MovieDetailsUIState(
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val releaseDate: String = "",
    val genres: String = "",
    val review: Int = 0,
    val specialNumber: Int = 0,
    val hours: Int = 0,
    val minutes: Int = 0,
    val voteAverage: String = "",
    val overview: String = "",
)