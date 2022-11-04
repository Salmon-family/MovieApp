package com.thechance.movie.ui.home.adapter

import com.devfalah.types.AllMediaType
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.models.MediaUiState
import com.thechance.movie.R

class TVShowAdapter(items: List<MediaUiState>, val listener: TVShowInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_tvshow
}

interface TVShowInteractionListener : BaseInteractionListener {
    fun onClickTVShow(tVShowID: Int)
    fun onClickSeeTVShow(type: AllMediaType)
}

