package com.thechance.ui.home.adapter

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.home.TVShowInteractionListener
import com.thechance.viewmodel.models.MediaUiState

class TVShowAdapter(items: List<MediaUiState>, val listener: TVShowInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_tvshow
}


