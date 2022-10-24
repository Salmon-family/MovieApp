package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.data.remote.response.movie.RatingDto
import com.karrar.movieapp.domain.models.*

data class MovieDetailsUIState(
    val movieId: Int = 0,
    val movieDetailsResult: MovieDetails = MovieDetails(),
    val movieCastResult: List<Actor> = emptyList(),
    val similarMoviesResult: List<Media> = emptyList(),
    val sessionIdResult: String? = "",
    val movieGetRatedResult: List<Rated> = emptyList(),
    val movieReview:List<Review> = emptyList(),
    val movieSetRatedResult: RatingDto = RatingDto(),
    val detailItemResult: List<DetailItem> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList(),
)

data class Error(
    val code: Int = 0,
    val message: String = "",
)