package com.karrar.movieapp.ui.movieDetails.movieDetailsUIState

data class MovieUIState(
    val movieDetailsResult: MovieDetailsUIState = MovieDetailsUIState(),
    val movieCastResult: List<ActorUiState> = emptyList(),
    val similarMoviesResult: List<MediaUIState> = emptyList(),
    val movieGetRatedResult: List<RatedUIState> = emptyList(),
    val movieReview: List<ReviewUIState> = emptyList(),
    val detailItemResult: List<DetailItemUIState> = mutableListOf(),
    val ratingValue: Float = 0F,
    val messageAppear: Boolean = false,
    val isLoading: Boolean = false,
    val errorUIStates: List<ErrorUIState> = emptyList(),
)