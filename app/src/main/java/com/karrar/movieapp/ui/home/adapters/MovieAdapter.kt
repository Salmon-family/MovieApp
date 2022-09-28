package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.enums.Type
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class MovieAdapter(items: List<Movie>, listener: MovieInteractionListener, type: Type) :
    BaseAdapter<Movie>(items, listener, type) {
    override val layoutID: Int = R.layout.item_movie
}

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieID: Int, type: Type)
}