package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.models.ActorUiState

class ActorAdapter(items: List<ActorUiState>, val layout: Int, val listener: ActorsInteractionListener) :
    BaseAdapter<ActorUiState>(items, listener) {
    override val layoutID: Int = layout
}

interface ActorsInteractionListener : BaseInteractionListener {
    fun onClickActor(actorID: Int)
}