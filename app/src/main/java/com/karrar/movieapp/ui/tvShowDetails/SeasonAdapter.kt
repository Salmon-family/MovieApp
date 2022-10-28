package com.karrar.movieapp.ui.tvShowDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Season
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.SeasonUIState

class SeasonAdapter(
    items: List<Season>,
    listener: SeasonInteractionListener
) : BaseAdapter<Season>(items, listener) {
    override val layoutID: Int = R.layout.item_season
}

class SeasonAdapterUIState(
    items: List<SeasonUIState>,
    listener: SeasonInteractionListener
) : BaseAdapter<SeasonUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_season
}

interface SeasonInteractionListener : BaseInteractionListener {
    fun onClickSeason(seasonNumber: Int)
}
