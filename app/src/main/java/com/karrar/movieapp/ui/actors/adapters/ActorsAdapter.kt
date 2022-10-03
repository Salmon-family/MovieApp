package com.karrar.movieapp.ui.actors.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.domain.models.Actor

class ActorsAdapter(items: List<Actor>, listener: ActorsInteractionListener) :
    BaseAdapter<Actor>(items, listener) {
    override val layoutID: Int = R.layout.item_actor
}

interface ActorsInteractionListener : BaseInteractionListener