package com.karrar.movieapp.ui.ratedmovies

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.RatedMovie
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class RatedMoviesAdapter(items: List<RatedMovie>, listener: RatedMoviesInteractionListener) :
    BaseAdapter<RatedMovie>(items, listener) {
    override val layoutID: Int = R.layout.item_rated_movie
}

interface RatedMoviesInteractionListener : BaseInteractionListener