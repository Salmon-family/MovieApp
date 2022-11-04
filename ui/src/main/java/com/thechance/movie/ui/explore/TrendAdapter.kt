package com.thechance.movie.ui.explore

import com.thechance.movie.R
import com.thechance.movie.ui.explore.exploreUIState.TrendyMediaUIState
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener


class TrendAdapter(items: List<TrendyMediaUIState>, listener: TrendInteractionListener)
    : BaseAdapter<TrendyMediaUIState>(items, listener){
    override val layoutID: Int = R.layout.item_trend
}

interface TrendInteractionListener : BaseInteractionListener {
    fun onClickTrend(item: TrendyMediaUIState)
}