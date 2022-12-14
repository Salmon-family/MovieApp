package com.karrar.movieapp.ui.actorDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.enums.HomeItemsType
import com.karrar.movieapp.ui.adapters.MovieInteractionListener

import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class ActorMoviesAdapter(items: List<ActorMoviesUIState>, val listener: MovieInteractionListener) :
    BaseAdapter<ActorMoviesUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_actor
}