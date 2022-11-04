package com.thechance.movie.ui.actors

import androidx.recyclerview.widget.DiffUtil
import com.thechance.movie.ui.adapters.ActorsInteractionListener
import com.thechance.movie.ui.base.BasePagingAdapter
import com.thechance.movie.ui.models.ActorUiState
import com.thechance.movie.R

class ActorsAdapter(listener: ActorsInteractionListener) :
    BasePagingAdapter<ActorUiState>(ActorComparator, listener) {

    override val layoutID: Int = R.layout.item_actor_see_all

    object ActorComparator : DiffUtil.ItemCallback<ActorUiState>() {
        override fun areItemsTheSame(oldItem: ActorUiState, newItem: ActorUiState) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ActorUiState, newItem: ActorUiState) =
            oldItem == newItem
    }
}


