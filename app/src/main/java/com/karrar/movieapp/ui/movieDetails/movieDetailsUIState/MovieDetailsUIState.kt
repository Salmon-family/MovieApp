package com.karrar.movieapp.ui.movieDetails.movieDetailsUIState

import com.karrar.movieapp.domain.enums.MediaType

data class MovieDetailsUIState(
    val id: Int = 0,
    val image: String = "",
    val name: String = "",
    val releaseDate: String = "",
    val genres: String = "",
    val review: Int = 0,
    val specialNumber: Int = 0,
    val voteAverage: String = "",
    val overview: String = "",
    val mediaType: MediaType = MediaType.MOVIE
)