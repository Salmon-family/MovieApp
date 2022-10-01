package com.karrar.movieapp.ui.search

import com.karrar.movieapp.R
import com.karrar.movieapp.base.BaseAdapter
import com.karrar.movieapp.base.BaseInteractionListener
import com.karrar.movieapp.domain.models.Media

class MediaAdapter(items: List<Media>, listener: MediaInteractionListener)
    : BaseAdapter<Media>(items, listener){
    override val layoutID: Int = R.layout.item_media
}

interface MediaInteractionListener : BaseInteractionListener {
    fun onClickMedia(mediaID: Int)
}