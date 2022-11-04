package com.thechance.movie.ui.tvShowDetails.tvShowUIState

import androidx.lifecycle.ViewModel
import com.thechance.movie.ui.models.ActorUiState

sealed class DetailItemUIState(val priority: Int) {

    class Header(val data: TvShowDetailsResultUIState) : DetailItemUIState(0)

    class Cast(val data: List<ActorUiState>) : DetailItemUIState(1)

    class Seasons(val data: List<SeasonUIState>) : DetailItemUIState(2)

    class Rating(val viewModel: ViewModel) : DetailItemUIState(3)

    object ReviewText : DetailItemUIState(4)

    class Comment(val data: ReviewUIState) : DetailItemUIState(5)

    object SeeAllReviewsButton : DetailItemUIState(6)
}
