package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.home.HomeInteractionListener

class PopularMovieAdapter(items: List<PopularMovie>, listener: HomeInteractionListener) :
    BaseAdapter<PopularMovie>(items, listener) {
    override val layoutID: Int = R.layout.item_popular_movie
}

