package com.thechance.movie.ui.profile.myratings

import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener

class RatedMoviesAdapter(items: List<RatedUIState>, listener: RatedMoviesInteractionListener) :
    BaseAdapter<RatedUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_rated_movie
}

interface RatedMoviesInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieId: Int)
}