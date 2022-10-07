package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class MediaAdapter(items: List<Media>, listener: MediaInteractionListener) :
    BaseAdapter<Media>(items, listener) {
    override val layoutID: Int = R.layout.item_media
}

interface MediaInteractionListener : BaseInteractionListener {
    fun onClickMedia(mediaId: Int)
}