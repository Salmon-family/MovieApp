package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.test.Movie

class PopularMovieAdapter(items: List<Movie>, listener: PopularMovieInteractionListener) :
    BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_popular_movie
}

interface PopularMovieInteractionListener : BaseInteractionListener {
    fun onClickPopularMovie(movieId: Int)
}