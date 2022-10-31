package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

data class TvShowDetailsUIState(
    val tvShowDetailsResult: TvShowDetailsResultUIState = TvShowDetailsResultUIState(),
    val seriesCastResult: List<SeriesCastUIState> = listOf(),
    val seriesSeasonsResult: List<SeasonUIState> = listOf(),
    val seriesRatedResult: List<RatedUIState> = listOf(),
    val seriesReviewsResult: List<ReviewUIState> = listOf(),
    val detailItemResult: List<DetailItemUIState> = listOf(),
    val watchHistoryUIState: WatchHistoryUIState = WatchHistoryUIState(),
    val ratingValue: Float = 0F,
    val isLoading: Boolean = false,
    val errorUIState: List<Error> = emptyList()
)
