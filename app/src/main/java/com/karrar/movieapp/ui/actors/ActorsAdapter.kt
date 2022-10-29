package com.karrar.movieapp.ui.actors

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.adapters.ActorsInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter
import com.karrar.movieapp.ui.models.ActorUiState

class ActorsAdapter(listener: ActorsInteractionListener) :
    BasePagingAdapter<ActorUiState>(ActorComparator, listener) {

    override val layoutID: Int = R.layout.item_actor

    object ActorComparator : DiffUtil.ItemCallback<ActorUiState>() {
        override fun areItemsTheSame(oldItem: ActorUiState, newItem: ActorUiState) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ActorUiState, newItem: ActorUiState) =
            oldItem == newItem
    }
}


