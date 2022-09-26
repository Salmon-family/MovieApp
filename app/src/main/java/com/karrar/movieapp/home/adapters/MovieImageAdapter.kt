package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Movie


class MovieImageAdapter(items: List<Movie>) :
    BaseAdapter<Movie>(items) {

    override val layoutID: Int = R.layout.item_movie_image

    override fun areContentsSame(oldItem: Movie, newItem: Movie): Boolean {
        return true
    }
}

//class HorizontalImageAdapter(adapter: MovieImageAdapter) : HorizontalBaseAdapter<Movie>(adapter)