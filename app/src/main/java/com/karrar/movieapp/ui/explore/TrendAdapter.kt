package com.karrar.movieapp.ui.explore

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Trend
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class TrendAdapter(items: List<Trend>, listener: TrendInteractionListener)
    : BaseAdapter<Trend>(items, listener){
    override val layoutID: Int = R.layout.item_trend
}

interface TrendInteractionListener : BaseInteractionListener {
    fun onClickTrend(trendID: Int, trendType: String)
}