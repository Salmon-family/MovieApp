package com.karrar.movieapp.domain.models

data class MovieDetails(
    val movie_cover:String?,
    val movie_name: String?,
    val release_date: String?,
    val genres: String?,
    val duration: Int?,
    val review: Int?,
    val vote_average: String?,
    val over_view: String?
)
