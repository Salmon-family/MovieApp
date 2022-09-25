package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.HorizontalBaseAdapter


class MovieImageAdapter(items: List<String>) :
    BaseAdapter<String>(items) {

    override val layoutID: Int = R.layout.item_movie_image

    override fun areContentsSame(oldItem: String, newItem: String): Boolean {
        return true
    }
}

class HorizontalImageAdapter(adapter: MovieImageAdapter) :
    HorizontalBaseAdapter<MovieImageAdapter>(adapter) {
    override val layoutID: Int = R.layout.layout_base_recycler
}