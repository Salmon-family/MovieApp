package com.thechance.viewmodel.movieDetails

import androidx.lifecycle.ViewModel
import com.thechance.viewmodel.models.ActorUiState
import com.thechance.viewmodel.models.MediaUiState


//sealed class DetailItem(val priority: Int) {
//
//    class Header(val data: MediaDetails) : DetailItem(0)
//
//    class Cast(val data: List<ActorUiState>) : DetailItem(1)
//
//    class SimilarMovies(val data: List<MediaUiState>) : DetailItem(2)
//
//    class Seasons(val data: List<Season>) : DetailItem(3)
//
//    class Rating(val viewModel: ViewModel) : DetailItem(4)
//
//    object ReviewText : DetailItem(5)
//
//    class Comment(val data: Review) : DetailItem(6)
//
//    object SeeAllReviewsButton : DetailItem(7)
//
//}