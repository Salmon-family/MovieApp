package com.thechance.movie.ui.tvShowDetails

import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.tvShowDetails.tvShowUIState.SeasonUIState

class SeasonAdapterUIState(
    items: List<SeasonUIState>,
    listener: SeasonInteractionListener
) : BaseAdapter<SeasonUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_season
}

interface SeasonInteractionListener : BaseInteractionListener {
    fun onClickSeason(seasonNumber: Int)
}
