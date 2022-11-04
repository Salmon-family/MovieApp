package com.thechance.movie.ui.movieDetails.movieDetailsUIState

import androidx.lifecycle.ViewModel
import com.thechance.movie.ui.models.ActorUiState
import com.thechance.movie.ui.models.MediaUiState

sealed class DetailItemUIState(val priority: Int) {

    class Header(val data: MovieDetailsUIState?) : DetailItemUIState(0)

    class Cast(val data: List<ActorUiState>) : DetailItemUIState(1)

    class SimilarMovies(val data: List<MediaUiState>) : DetailItemUIState(2)

    class Comment(val data: ReviewUIState) : DetailItemUIState(6)

    class Rating(val viewModel: ViewModel) : DetailItemUIState(4)

    object ReviewText : DetailItemUIState(5)

    object SeeAllReviewsButton : DetailItemUIState(7)

}