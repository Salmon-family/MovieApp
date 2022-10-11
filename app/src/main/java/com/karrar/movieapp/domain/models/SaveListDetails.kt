package com.karrar.movieapp.domain.models


data class SaveListDetails(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterPath: String,
)