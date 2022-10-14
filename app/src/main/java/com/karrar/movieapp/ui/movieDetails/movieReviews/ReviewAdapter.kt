package com.karrar.movieapp.ui.movieDetails.movieReviews

import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.domain.models.Review
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class ReviewAdapter(items: List<Review>, listener: BaseInteractionListener
): BaseAdapter<Review>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_review

}

