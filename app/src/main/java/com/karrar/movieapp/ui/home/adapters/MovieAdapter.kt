package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.home.HomeInteractionListener

class MovieAdapter(items: List<Media>,val listener: HomeInteractionListener) :
    BaseAdapter<Media>(items, listener) {
    override val layoutID: Int = R.layout.item_movie
}

