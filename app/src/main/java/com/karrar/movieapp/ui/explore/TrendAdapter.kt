package com.karrar.movieapp.ui.explore

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.*

class TrendAdapter(items: List<TrendyMediaUIState>, listener: TrendInteractionListener)
    : BaseAdapter<TrendyMediaUIState>(items, listener){
    override val layoutID: Int = R.layout.item_trend
}

interface TrendInteractionListener : BaseInteractionListener {
    fun onClickTrend(trendID: Int, trendType: String)
}