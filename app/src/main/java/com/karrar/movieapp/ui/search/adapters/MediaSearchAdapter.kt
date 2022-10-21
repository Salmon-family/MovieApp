package com.karrar.movieapp.ui.search.adapters

import androidx.recyclerview.widget.DiffUtil
import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.base.BasePagingAdapter

class MediaSearchAdapter(listener: MediaSearchInteractionListener)
    : BasePagingAdapter<Media>(MediaSearchComparator, listener){
    override val layoutID: Int = R.layout.item_media_search

    object MediaSearchComparator : DiffUtil.ItemCallback<Media>(){
        override fun areItemsTheSame(oldItem: Media, newItem: Media) =
            oldItem.mediaID == newItem.mediaID

        override fun areContentsTheSame(oldItem: Media, newItem: Media) =
            oldItem == newItem
    }
}

interface MediaSearchInteractionListener : BaseInteractionListener {
    fun onClickMediaResult(media: Media)
}