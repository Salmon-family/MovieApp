package com.karrar.movieapp.ui.tvShowDetails.episodes

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Episode
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class EpisodeAdapter(items: List<Episode>, listener: EpisodesInteractionListener) :
    BaseAdapter<Episode>(items, listener) {
    override val layoutID: Int = R.layout.item_episode
}

interface EpisodesInteractionListener : BaseInteractionListener