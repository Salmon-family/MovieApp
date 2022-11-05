package com.thechance.ui.tvShowDetails

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.tvShowDetails.episodes.SeasonInteractionListener
import com.thechance.viewmodel.tvShowDetails.tvShowUIState.SeasonUIState

class SeasonAdapterUIState(
    items: List<SeasonUIState>,
    listener: SeasonInteractionListener
) : BaseAdapter<SeasonUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_season
}


