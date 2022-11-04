package com.karrar.movieapp.ui.home.adapter

import com.karrar.movieapp.R
import com.devfalah.types.AllMediaType
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.models.MediaUiState

class TVShowAdapter(items: List<MediaUiState>,val listener: TVShowInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_tvshow
}

interface TVShowInteractionListener : BaseInteractionListener {
    fun onClickTVShow(tVShowID: Int)
    fun onClickSeeTVShow(type: AllMediaType)
}

