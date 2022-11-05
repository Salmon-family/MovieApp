package com.thechance.ui.adapters

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.models.MediaUiState
import com.thechance.viewmodel.movieDetails.MovieInteractionListener

class MovieAdapter(items: List<MediaUiState>, val listener: MovieInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_movie
}


