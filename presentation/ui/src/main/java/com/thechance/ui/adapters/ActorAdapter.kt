package com.thechance.ui.adapters

import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.models.ActorUiState
import com.thechance.viewmodel.movieDetails.ActorsInteractionListener

class ActorAdapter(
    items: List<ActorUiState>,
    val layout: Int,
    val listener: ActorsInteractionListener
) :
    BaseAdapter<ActorUiState>(items, listener) {
    override val layoutID: Int = layout
}

