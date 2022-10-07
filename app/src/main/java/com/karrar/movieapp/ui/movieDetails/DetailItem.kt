package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.domain.enums.MovieType
import com.karrar.movieapp.domain.models.*
import com.karrar.movieapp.ui.home.HomeRecyclerItem


sealed class DetailItem(val priority: Int) {

    class Header(val data : MovieDetails) : DetailItem(0)

    class Cast(val data : List<Actor>) : DetailItem(1)

    class SimilarMovies (val data: List<Media>) : DetailItem(2)

    class Rating (val viewModel: MovieDetailsViewModel) : DetailItem(3)

    class Comment (val data: List<Review>,val viewModel: MovieDetailsViewModel) : DetailItem(4)

}