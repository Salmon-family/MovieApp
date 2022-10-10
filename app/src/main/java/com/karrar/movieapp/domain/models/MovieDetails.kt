package com.karrar.movieapp.domain.models

data class MovieDetails(
    val movieID1: Int,
    val movieImage1: String,
    val movieName1: String,
    val releaseDate1: String,
    val genres1: String,
    val duration1: Int,
    val review1: Int,
    val voteAverage1: String,
    val overView1: String
) : MediaDetails(
    movieID1,
    movieImage1,
    movieName1,
    releaseDate1,
    genres1,
    duration1,
    review1,
    voteAverage1,
    overView1,
    emptyList()
)
