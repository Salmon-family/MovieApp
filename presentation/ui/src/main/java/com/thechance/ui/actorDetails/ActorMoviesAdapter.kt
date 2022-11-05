package com.thechance.ui.actorDetails

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.actorDetails.ActorMoviesUIState
import com.thechance.viewmodel.movieDetails.MovieInteractionListener

class ActorMoviesAdapter(items: List<ActorMoviesUIState>, val listener: MovieInteractionListener) :
    BaseAdapter<ActorMoviesUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_movie_actor
}