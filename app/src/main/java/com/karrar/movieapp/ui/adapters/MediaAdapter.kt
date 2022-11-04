package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.models.MediaUiState

class MediaAdapter(items: List<MediaUiState>, layout: Int, listener: MediaInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = layout
}

interface MediaInteractionListener : BaseInteractionListener {
    fun onClickMedia(mediaId: Int)
}