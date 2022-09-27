package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.test.Movie

class BannerAdapter(items: List<Movie>, listener: BannerInteractionListener) :
    BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_banner
}

interface BannerInteractionListener : BaseInteractionListener {
    fun onClickBanner(name: String)
}