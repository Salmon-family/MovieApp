package com.karrar.movieapp.domain.models

data class MovieDetails(
    val movieId: Int,
    val movieImage: String,
    val movieName: String,
    val movieReleaseDate: String,
    val movieGenres: String,
    val movieDuration: Int,
    val movieReview: Int,
    val movieVoteAverage: String,
    val movieOverview: String,
)