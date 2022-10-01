package com.karrar.movieapp.domain.models

data class Media(
    val type: String?,
    val id: Int?,
    val movieName: String?,
    val seriesName: String?,
    val actorName: String?,
    val releaseDate: String?,
    val firstAirDate: String?,
    val rate: Float?,
    val imagePath: String?,
    val profilePath: String?
)
