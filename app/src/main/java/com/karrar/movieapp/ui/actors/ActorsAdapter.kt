package com.karrar.movieapp.ui.actors

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter

class ActorsAdapter(listener: ActorsInteractionListener) :
    BasePagingAdapter<Actor>(ActorComparator, listener) {

    override val layoutID: Int = R.layout.item_actor

    object ActorComparator : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor) =
            oldItem.actorID == newItem.actorID

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor) =
            oldItem == newItem
    }
}


