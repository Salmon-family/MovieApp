package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Series
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener


class SeriesAdapter(items: List<Series>, listener: SeriesInteractionListener) :
    BaseAdapter<Series>(items, listener, null) {
    override val layoutID: Int = R.layout.item_on_the_air_today
}

interface SeriesInteractionListener : BaseInteractionListener {
    fun onClickSeries(seriesID: Int)
}