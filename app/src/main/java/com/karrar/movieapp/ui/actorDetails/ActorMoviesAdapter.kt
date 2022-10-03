package com.karrar.movieapp.ui.actorDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.ui.base.BaseAdapter

class ActorMoviesAdapter(
    items: List<Movie>, listener: MovieInteractionListener,
) : BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_media
}