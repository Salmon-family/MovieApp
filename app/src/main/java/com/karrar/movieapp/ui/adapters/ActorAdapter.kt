package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class ActorAdapter(items: List<Actor>, val layout: Int, val listener: ActorsInteractionListener) :
    BaseAdapter<Actor>(items, listener) {
    override val layoutID: Int = layout
}

interface ActorsInteractionListener : BaseInteractionListener {
    fun onClickActor(actorID: Int)
}