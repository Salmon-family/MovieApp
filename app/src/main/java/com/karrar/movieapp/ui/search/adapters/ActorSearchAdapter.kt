package com.karrar.movieapp.ui.search.adapters

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter
import com.karrar.movieapp.ui.search.mediaSearchUIState.MediaUIState

class ActorSearchAdapter(listener: ActorSearchInteractionListener)
    : BasePagingAdapter<MediaUIState>(ActorSearchComparator, listener){
    override val layoutID: Int = R.layout.item_actor_search

    object ActorSearchComparator : DiffUtil.ItemCallback<MediaUIState>(){
        override fun areItemsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem == newItem
    }
}

interface ActorSearchInteractionListener : BaseInteractionListener{
    fun onClickActorResult(personID: Int, name: String)
}