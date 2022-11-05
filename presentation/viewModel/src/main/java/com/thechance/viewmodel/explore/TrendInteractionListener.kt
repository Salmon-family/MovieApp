package com.thechance.viewmodel.category.com.thechance.viewmodel.explore

import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.explore.exploreUIState.TrendyMediaUIState

interface TrendInteractionListener : BaseInteractionListener {
    fun onClickTrend(item: TrendyMediaUIState)
}