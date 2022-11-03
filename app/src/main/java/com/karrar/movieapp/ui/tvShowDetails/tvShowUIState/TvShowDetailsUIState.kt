package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

data class TvShowDetailsUIState(
    val tvShowDetailsResult: TvShowDetailsResultUIState = TvShowDetailsResultUIState(),
    val seriesCastResult: List<SeriesCastUIState> = listOf(),
    val seriesSeasonsResult: List<SeasonUIState> = listOf(),
    val seriesReviewsResult: List<ReviewUIState> = listOf(),
    val detailItemResult: List<DetailItemUIState> = listOf(),
    val ratingValue: Float = 0F,
    val isLoading: Boolean = false,
    val isLogin: Boolean = false,
    val errorUIState: List<Error> = emptyList()
)
