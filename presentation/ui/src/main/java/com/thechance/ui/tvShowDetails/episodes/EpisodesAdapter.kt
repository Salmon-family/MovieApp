package com.thechance.ui.tvShowDetails.episodes

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.tvShowDetails.episodes.EpisodesInteractionListener
import com.thechance.viewmodel.tvShowDetails.episodes.EpisodesUIState

class EpisodeAdapter(items: List<EpisodesUIState>, listener: EpisodesInteractionListener) :
    BaseAdapter<EpisodesUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_episode
}

