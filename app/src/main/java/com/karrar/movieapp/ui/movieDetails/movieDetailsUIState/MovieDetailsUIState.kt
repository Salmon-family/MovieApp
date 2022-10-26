package com.karrar.movieapp.ui.movieDetails.movieDetailsUIState

data class MovieDetailsUIState(
    val mediaDetailsResult: MediaDetailsUIState? = MediaDetailsUIState(),
    val movieCastResult: List<ActorUiState> = emptyList(),
    val similarMoviesResult: List<MediaUIState> = emptyList(),
    val movieGetRatedResult: List<RatedUIState> = emptyList(),
    val movieReview: List<ReviewUIState> = emptyList(),
    val movieSetRatedResult: RatingUIState? = RatingUIState(),
    val detailItemResult: MutableList<DetailItemUIState> = mutableListOf(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList(),
)