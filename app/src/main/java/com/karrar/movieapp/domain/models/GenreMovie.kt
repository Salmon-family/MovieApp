package com.karrar.movieapp.domain.models

data class GenreMovie(
    val genreID: Int,
    var genreName: String?,
    val movies: List<Media>?
)