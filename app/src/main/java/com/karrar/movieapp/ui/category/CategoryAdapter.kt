package com.karrar.movieapp.ui.category

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter

class CategoryAdapter (listener: MediaInteractionListener) :
    BasePagingAdapter<MediaUIState>(MediaComparator, listener) {
    override val layoutID: Int = R.layout.item_category

    object MediaComparator : DiffUtil.ItemCallback<MediaUIState>() {
        override fun areItemsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem == newItem
    }

}
