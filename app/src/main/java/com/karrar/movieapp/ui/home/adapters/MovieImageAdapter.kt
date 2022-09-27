package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.test.Movie

class MovieImageAdapter(items: List<Movie>, listener: MovieInteractionListener) :
    BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_movie
}

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(name: String)
}