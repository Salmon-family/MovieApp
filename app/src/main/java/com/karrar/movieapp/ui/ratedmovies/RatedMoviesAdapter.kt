package com.karrar.movieapp.ui.ratedmovies

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.RatedMovies
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class RatedMoviesAdapter(items: List<RatedMovies>, listener: RatedMoviesInteractionListener) :
    BaseAdapter<RatedMovies>(items, listener) {
    override val layoutID: Int = R.layout.item_rated_movie
}

interface RatedMoviesInteractionListener : BaseInteractionListener