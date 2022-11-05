package com.thechance.ui.category

import androidx.recyclerview.widget.DiffUtil
import com.thechance.ui.R
import com.thechance.ui.base.BasePagingAdapter
import com.thechance.viewmodel.allMedia.MediaInteractionListener
import com.thechance.viewmodel.category.uiState.MediaUIState

class CategoryAdapter(listener: MediaInteractionListener) :
    BasePagingAdapter<MediaUIState>(MediaComparator, listener) {
    override val layoutID: Int = R.layout.item_category

    object MediaComparator : DiffUtil.ItemCallback<MediaUIState>() {
        override fun areItemsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem == newItem
    }

}
