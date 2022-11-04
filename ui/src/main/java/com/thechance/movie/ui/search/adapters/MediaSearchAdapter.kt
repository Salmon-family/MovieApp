package com.thechance.movie.ui.search.adapters

import androidx.recyclerview.widget.DiffUtil
import com.thechance.movie.ui.search.mediaSearchUIState.MediaUIState
import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.base.BasePagingAdapter


class MediaSearchAdapter(listener: MediaSearchInteractionListener)
    : BasePagingAdapter<MediaUIState>(MediaSearchComparator, listener){
    override val layoutID: Int = R.layout.item_media_search

    object MediaSearchComparator : DiffUtil.ItemCallback<MediaUIState>(){
        override fun areItemsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem == newItem
    }
}

interface MediaSearchInteractionListener : BaseInteractionListener {
    fun onClickMediaResult(media: MediaUIState)
}