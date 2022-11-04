package com.thechance.movie.ui.home.adapter

import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.home.HomeInteractionListener
import com.thechance.movie.ui.home.homeUiState.PopularUiState
import com.thechance.movie.R

class PopularMovieAdapter(items: List<PopularUiState>, listener: HomeInteractionListener) :
    BaseAdapter<PopularUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_popular_movie
}

