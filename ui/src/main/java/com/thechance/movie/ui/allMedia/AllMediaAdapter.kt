package com.thechance.movie.ui.allMedia

import androidx.recyclerview.widget.DiffUtil
import com.thechance.movie.ui.adapters.MediaInteractionListener
import com.thechance.movie.ui.base.BasePagingAdapter
import com.thechance.movie.ui.models.MediaUiState
import com.thechance.movie.R

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
