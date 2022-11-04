package com.karrar.movieapp.ui.reviews

import com.karrar.movieapp.R
import com.devfalah.models.Review
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class ReviewAdapter(items: List<com.devfalah.models.Review>, listener: BaseInteractionListener
): BaseAdapter<com.devfalah.models.Review>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_review

}

