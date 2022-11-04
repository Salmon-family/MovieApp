package com.thechance.movie.ui.tvShowDetails.episodes

import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener

class EpisodeAdapter(items: List<EpisodesUIState>, listener: EpisodesInteractionListener) :
    BaseAdapter<EpisodesUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_episode
}

interface EpisodesInteractionListener : BaseInteractionListener
