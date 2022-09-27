package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Movie
import com.karrar.movieapp.home.HomeViewModel

class MovieImageAdapter(items: List<Movie>, listener: MovieInteractionListener) :
    BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_movie
}

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(name: String)
}