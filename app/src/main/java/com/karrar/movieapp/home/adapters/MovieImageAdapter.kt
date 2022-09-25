package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter


class MovieImageAdapter(items: List<String>) :
    BaseAdapter<String>(items) {

    override val layoutID: Int = R.layout.item_movie_image

    override fun areContentsSame(oldItem: String, newItem: String): Boolean {
        return true
    }
}

class HorizontalImageAdapter(adapter: MovieImageAdapter) : HorizontalBaseAdapter<String>(adapter)