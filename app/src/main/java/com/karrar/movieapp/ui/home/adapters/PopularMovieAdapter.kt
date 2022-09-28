package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class PopularMovieAdapter(items: List<PopularMovie>, listener: PopularMovieInteractionListener) :
    BaseAdapter<PopularMovie>(items, listener, null) {
    override val layoutID: Int = R.layout.item_popular_movie
}

interface PopularMovieInteractionListener : BaseInteractionListener {
    fun onClickPopularMovie(movieId: Int)
}