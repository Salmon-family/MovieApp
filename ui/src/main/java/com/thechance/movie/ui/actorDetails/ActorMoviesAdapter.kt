package com.thechance.movie.ui.actorDetails

import com.thechance.movie.ui.adapters.MovieInteractionListener

import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.R

class ActorMoviesAdapter(items: List<ActorMoviesUIState>, val listener: MovieInteractionListener) :
    BaseAdapter<ActorMoviesUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_actor
}