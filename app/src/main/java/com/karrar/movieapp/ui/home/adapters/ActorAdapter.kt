package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.PopularMovie
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener


class ActorAdapter(items: List<PopularMovie>, listener: ActorInteractionListener) :
    BaseAdapter<PopularMovie>(items, listener, null) {
    override val layoutID: Int = R.layout.item_actor
}

interface ActorInteractionListener : BaseInteractionListener {
    fun onClickActor(actorID: Int)
}