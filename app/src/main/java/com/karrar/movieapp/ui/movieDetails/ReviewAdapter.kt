package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.base.*
import com.karrar.movieapp.data.remote.response.movieDetailsDto.reviews.ReviewsDto

class ReviewAdapter(items: List<ReviewsDto>, listener: BaseInteractionListener
): BaseAdapter<ReviewsDto>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_review

}

