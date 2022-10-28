package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

import androidx.lifecycle.ViewModel

sealed class DetailItemUIState(val priority: Int) {

    class Header(val data: TvShowDetailsResultUIState) : DetailItemUIState(0)

    class Cast(val data: List<SeriesCastUIState>) : DetailItemUIState(1)

    class Seasons(val data: List<SeasonUIState>) : DetailItemUIState(2)

    class Rating(val viewModel: ViewModel) : DetailItemUIState(3)

    object ReviewText : DetailItemUIState(4)

    class Comment(val data: ReviewUIState) : DetailItemUIState(5)

    object SeeAllReviewsButton : DetailItemUIState(6)
}
