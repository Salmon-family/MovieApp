package com.karrar.movieapp.domain.models


data class SaveListDetails(
    val id: Int,
    val mediaType: String?,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterPath: String,
)