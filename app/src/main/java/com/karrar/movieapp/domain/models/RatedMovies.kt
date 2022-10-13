package com.karrar.movieapp.domain.models

data class RatedMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val rating: Float,
    val releaseDate: String,
)
