package com.karrar.movieapp.ui.home.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.data.test.Movie


class ActorAdapter(items: List<Movie>, listener: ActorInteractionListener) :
    BaseAdapter<Movie>(items, listener) {
    override val layoutID: Int = R.layout.item_actor
}

interface ActorInteractionListener : BaseInteractionListener {
    fun onClickActor(actorID: Int)
}