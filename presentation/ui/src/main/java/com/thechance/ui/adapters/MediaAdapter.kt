package com.thechance.ui.adapters

import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.allMedia.MediaInteractionListener
import com.thechance.viewmodel.models.MediaUiState

class MediaAdapter(items: List<MediaUiState>, layout: Int, listener: MediaInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = layout
}
