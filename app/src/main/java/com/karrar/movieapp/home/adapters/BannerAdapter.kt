package com.karrar.movieapp.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.base.HorizontalBaseAdapter
import com.karrar.movieapp.data.Movie
import com.karrar.movieapp.home.HomeViewModel

class BannerAdapter(items: List<Movie>, listener: BannerInteractionListener) :
    BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_banner
}

class BannerHorizontalAdapter(adapter: BannerAdapter, viewModel: HomeViewModel) :
    HorizontalBaseAdapter<Movie, HomeViewModel>(adapter, viewModel) {
    override val layoutID = R.layout.recycler_banner
}

interface BannerInteractionListener : BaseInteractionListener {
    fun onClickBanner(name: String)
}