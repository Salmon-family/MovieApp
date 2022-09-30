package com.karrar.movieapp.ui.actorDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.domain.models.Movie


class MovieAdapter(
    items: List<Movie>, listener: MovieInteractionListener,
) : BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_movie
}

