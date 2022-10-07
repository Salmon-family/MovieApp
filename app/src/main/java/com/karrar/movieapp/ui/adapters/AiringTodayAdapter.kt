package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.home.HomeInteractionListener

class AiringTodayAdapter(items: List<Media>, listener: HomeInteractionListener) :
    BaseAdapter<Media>(items, listener) {
    override val layoutID: Int = R.layout.item_airing_today
}

