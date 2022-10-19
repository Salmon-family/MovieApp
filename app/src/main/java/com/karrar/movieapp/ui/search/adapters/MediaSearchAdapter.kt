package com.karrar.movieapp.ui.search.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class MediaSearchAdapter(items: List<Media>, listener: MediaSearchInteractionListener)
    : BaseAdapter<Media>(items, listener){
    override val layoutID: Int = R.layout.item_media_search
}

interface MediaSearchInteractionListener : BaseInteractionListener {
    fun onClickMedia(media: Media)
}