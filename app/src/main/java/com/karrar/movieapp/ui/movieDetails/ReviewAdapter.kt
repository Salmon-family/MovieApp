package com.karrar.movieapp.ui.movieDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.base.*
import com.karrar.movieapp.data.remote.response.movieDetailsDto.reviews.Reviews

class ReviewAdapter(items: List<Reviews>, listener: BaseInteractionListener
): BaseAdapter<Reviews>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_review

}

