package com.thechance.ui.reviews

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.base.BaseInteractionListener

class ReviewAdapter(
    items: List<Review>, listener: BaseInteractionListener
) : BaseAdapter<Review>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_review

}

