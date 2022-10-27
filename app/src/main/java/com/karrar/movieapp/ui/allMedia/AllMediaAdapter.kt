package com.karrar.movieapp.ui.allMedia

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter
import com.karrar.movieapp.ui.models.MediaUiState

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
