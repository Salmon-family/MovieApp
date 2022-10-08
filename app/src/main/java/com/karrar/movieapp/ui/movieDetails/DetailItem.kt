package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.domain.models.*


sealed class DetailItem(val priority: Int) {

    class Header(val data : MovieDetails) : DetailItem(0)

    class Cast(val data : List<Actor>) : DetailItem(1)

    class SimilarMovies (val data: List<Media>) : DetailItem(2)

    class Rating (val viewModel: MovieDetailsViewModel) : DetailItem(3)

    object ReviewText : DetailItem(4)

    class Comment (val data: Review) : DetailItem(5)

    object SeeAllReviewsButton  : DetailItem(6)

}