package com.thechance.movie.ui.category

import androidx.recyclerview.widget.DiffUtil
import com.thechance.movie.R
import com.thechance.movie.ui.adapters.MediaInteractionListener
import com.thechance.movie.ui.base.BasePagingAdapter
import com.thechance.movie.ui.category.uiState.MediaUIState

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
