package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.ActorUiState

class ActorAdapter(items: List<Actor>, val layout: Int, val listener: ActorsInteractionListener) :
    BaseAdapter<Actor>(items, listener) {
    override val layoutID: Int = layout
}

class ActorAdapter2(items: List<ActorUiState>, val layout: Int, val listener: ActorsInteractionListener) :
    BaseAdapter<ActorUiState>(items, listener) {
    override val layoutID: Int = layout
}

interface ActorsInteractionListener : BaseInteractionListener {
    fun onClickActor(actorID: Int)
}