package com.thechance.movie.ui.adapters

import com.devfalah.types.HomeItemsType
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.models.MediaUiState
import com.thechance.movie.R

class MovieAdapter(items: List<MediaUiState>, val listener: MovieInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_movie
}

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieId: Int)
    fun onClickSeeAllMovie(homeItemsType: HomeItemsType)
}

