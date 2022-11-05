package com.thechance.ui.allMedia

import androidx.recyclerview.widget.DiffUtil
import com.thechance.ui.R
import com.thechance.ui.base.BasePagingAdapter
import com.thechance.viewmodel.allMedia.MediaInteractionListener
import com.thechance.viewmodel.models.MediaUiState

open class AllMediaAdapter(listener: MediaInteractionListener) :
    BasePagingAdapter<MediaUiState>(MediaComparator, listener) {
    override val layoutID: Int = R.layout.item_media

    object MediaComparator : DiffUtil.ItemCallback<MediaUiState>() {
        override fun areItemsTheSame(oldItem: MediaUiState, newItem: MediaUiState) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MediaUiState, newItem: MediaUiState) =
            oldItem == newItem
    }

}
