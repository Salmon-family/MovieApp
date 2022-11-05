package com.thechance.ui.search.adapters

import androidx.recyclerview.widget.DiffUtil
import com.thechance.ui.R
import com.thechance.ui.base.BasePagingAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.search.MediaSearchInteractionListener
import com.thechance.viewmodel.search.mediaSearchUIState.MediaUIState


class MediaSearchAdapter(listener: MediaSearchInteractionListener) :
    BasePagingAdapter<MediaUIState>(MediaSearchComparator, listener) {
    override val layoutID: Int = R.layout.item_media_search

    object MediaSearchComparator : DiffUtil.ItemCallback<MediaUIState>() {
        override fun areItemsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem == newItem
    }
}

