package com.thechance.ui.actors

import androidx.recyclerview.widget.DiffUtil
import com.thechance.ui.R
import com.thechance.ui.base.BasePagingAdapter
import com.thechance.viewmodel.models.ActorUiState
import com.thechance.viewmodel.movieDetails.ActorsInteractionListener

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


