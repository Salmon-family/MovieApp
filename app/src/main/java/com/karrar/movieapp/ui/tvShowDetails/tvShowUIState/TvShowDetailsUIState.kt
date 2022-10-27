package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

data class TvShowDetailsUIState(
    val tvShowDetailsResult: TvShowDetailsResultUIState = TvShowDetailsResultUIState(),
    val seriesCastResult: List<SeriesCastUIState> = listOf(),
    val seriesSeasonsResult: List<SeasonUIState> = listOf(),
    val seriesRatedResult: List<RatedUIState> = listOf(),
    val seriesGetRatedResult: RatingUIState = RatingUIState(),
    val seriesReviewsResult: List<ReviewUIState> = listOf(),
    val detailItemResult: List<DetailItemUIState> = listOf(),
    val watchHistoryUIState: WatchHistoryUIState = WatchHistoryUIState(),
    val seriesSetRatedResult: Int = 0,
    val ratingValue: Float = 0F,
    val messageAppear: Boolean = false,
    val isLoading: Boolean = false,
    val error: List<Error>? = emptyList(),
)





