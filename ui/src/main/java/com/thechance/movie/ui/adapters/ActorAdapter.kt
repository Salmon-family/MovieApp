package com.thechance.movie.ui.adapters

import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.models.ActorUiState

class ActorAdapter(items: List<ActorUiState>, val layout: Int, val listener: ActorsInteractionListener) :
    BaseAdapter<ActorUiState>(items, listener) {
    override val layoutID: Int = layout
}

interface ActorsInteractionListener : BaseInteractionListener {
    fun onClickActor(actorID: Int)
}