package com.karrar.movieapp.ui.actorDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseAdapter

class ActorMoviesAdapter(
    items: List<Media>, listener: MovieInteractionListener,
) : BaseAdapter<Media>(items, listener) {
    override val layoutID: Int = R.layout.item_media_search
}