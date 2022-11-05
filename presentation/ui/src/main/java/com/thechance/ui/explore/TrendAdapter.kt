package com.thechance.ui.explore

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.explore.TrendInteractionListener
import com.thechance.viewmodel.explore.exploreUIState.TrendyMediaUIState


class TrendAdapter(items: List<TrendyMediaUIState>, listener: TrendInteractionListener) :
    BaseAdapter<TrendyMediaUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_trend
}

