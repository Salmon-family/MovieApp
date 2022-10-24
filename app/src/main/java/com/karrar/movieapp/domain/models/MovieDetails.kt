package com.karrar.movieapp.domain.models

import com.karrar.movieapp.domain.enums.MediaType

data class MovieDetails(
    val movieId: Int = 0,
    val movieImage: String = "",
    val movieName: String = "",
    val movieReleaseDate: String = "",
    val movieGenres: String = "",
    val movieDuration: Int = 0,
    val movieReview: Int = 0,
    val movieVoteAverage: String = "",
    val movieOverview: String = "",
    val movieType: MediaType = MediaType.MOVIE
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
