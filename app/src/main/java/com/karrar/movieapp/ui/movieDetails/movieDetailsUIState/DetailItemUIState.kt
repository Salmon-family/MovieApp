package com.karrar.movieapp.ui.movieDetails.movieDetailsUIState

import androidx.lifecycle.ViewModel

sealed class DetailItemUIState(val priority: Int) {

    class Header(val data: MediaDetailsUIState?) : DetailItemUIState(0)

    class Cast(val data: List<ActorUiState>) : DetailItemUIState(1)

    class SimilarMovies(val data: List<MediaUIState>) : DetailItemUIState(2)

    class Comment(val data: ReviewUIState) : DetailItemUIState(6)

    class Rating(val viewModel: ViewModel) : DetailItemUIState(4)

    object ReviewText : DetailItemUIState(5)

    object SeeAllReviewsButton : DetailItemUIState(7)

}