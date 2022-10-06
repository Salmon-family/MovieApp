package com.karrar.movieapp.ui.actors.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class ActorsAdapter(items: List<Actor>, listener: ActorsInteractionListener) :
    BaseAdapter<Actor>(items, listener) {
    override val layoutID: Int = R.layout.item_actor_search
}

interface ActorsInteractionListener : BaseInteractionListener