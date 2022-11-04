package com.karrar.movieapp.ui.adapters

import com.karrar.movieapp.R
import com.devfalah.types.HomeItemsType
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.models.MediaUiState

class MovieAdapter(items: List<MediaUiState>,val listener: MovieInteractionListener) :
    BaseAdapter<MediaUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_movie
}

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieId: Int)
    fun onClickSeeAllMovie(homeItemsType: com.devfalah.types.HomeItemsType)
}

