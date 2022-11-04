package com.thechance.movie.ui.adapters

import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.models.MediaUiState

class MediaAdapter(items: List<MediaUiState>, layout: Int, listener: MediaInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = layout
}

interface MediaInteractionListener : BaseInteractionListener {
    fun onClickMedia(mediaId: Int)
}