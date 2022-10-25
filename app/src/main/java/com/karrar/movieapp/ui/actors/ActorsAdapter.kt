package com.karrar.movieapp.ui.actors

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.actors.models.ActorInfoUIState
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter

class ActorsAdapter(listener: ActorsInteractionListener) :
    BasePagingAdapter<ActorInfoUIState>(ActorComparator, listener) {

    override val layoutID: Int = R.layout.item_actor

    object ActorComparator : DiffUtil.ItemCallback<ActorInfoUIState>() {
        override fun areItemsTheSame(oldItem: ActorInfoUIState, newItem: ActorInfoUIState) =
            oldItem.actorID == newItem.actorID

        override fun areContentsTheSame(oldItem: ActorInfoUIState, newItem: ActorInfoUIState) =
            oldItem == newItem
    }
}


