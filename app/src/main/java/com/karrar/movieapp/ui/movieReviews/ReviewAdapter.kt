package com.karrar.movieapp.ui.movieReviews

import com.karrar.movieapp.R
import com.karrar.movieapp.data.remote.response.review.ReviewsDto
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class ReviewAdapter(items: List<ReviewsDto>, listener: BaseInteractionListener
): BaseAdapter<ReviewsDto>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_review

}

