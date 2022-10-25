package com.karrar.movieapp.ui.allMedia

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter
import com.karrar.movieapp.ui.category.MediaUIState

open class AllMediaAdapter(listener: MediaInteractionListener) :
    BasePagingAdapter<MediaUIState>(MediaComparator, listener) {
    override val layoutID: Int = R.layout.item_media

    object MediaComparator : DiffUtil.ItemCallback<MediaUIState>() {
        override fun areItemsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: MediaUIState, newItem: MediaUIState) =
            oldItem == newItem
    }

}
