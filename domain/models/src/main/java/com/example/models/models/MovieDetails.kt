package com.example.models.models

import com.example.models.utilities.MediaType

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
    val movieType: MediaType
) : MediaDetails(
    movieId,
    movieImage,
    movieName,
    movieReleaseDate,
    movieGenres,
    movieDuration,
    movieReview,
    movieVoteAverage,
    movieOverview,
    emptyList(),
    movieType
)
