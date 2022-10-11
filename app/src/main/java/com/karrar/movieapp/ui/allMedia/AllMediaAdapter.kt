package com.karrar.movieapp.ui.allMedia

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter

class AllMediaAdapter(listener: MediaInteractionListener) :
    BasePagingAdapter<Media>(MediaComparator, listener) {
    override val layoutID: Int = R.layout.item_media

    object MediaComparator : DiffUtil.ItemCallback<Media>() {
        override fun areItemsTheSame(oldItem: Media, newItem: Media) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: Media, newItem: Media) =
            oldItem == newItem
    }

}
