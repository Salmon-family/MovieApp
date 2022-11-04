package com.thechance.movie.ui.reviews

import com.devfalah.models.Review
import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener

class ReviewAdapter(items: List<com.devfalah.models.Review>, listener: BaseInteractionListener
): BaseAdapter<Review>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_review

}

