package com.karrar.movieapp.ui.allMedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karrar.movieapp.R
import com.karrar.movieapp.databinding.ItemMediaBinding
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.adapters.MediaInteractionListener
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter
import javax.inject.Inject

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
