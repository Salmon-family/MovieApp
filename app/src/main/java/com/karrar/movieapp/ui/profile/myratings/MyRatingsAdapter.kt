package com.karrar.movieapp.ui.profile.myratings

import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class RatedMoviesAdapter(items: List<RatedUIState>, listener: RatedMoviesInteractionListener) :
    BaseAdapter<RatedUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_rated_movie
}

interface RatedMoviesInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieId: Int)
}