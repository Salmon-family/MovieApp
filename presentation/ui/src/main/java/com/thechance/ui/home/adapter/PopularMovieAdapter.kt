package com.thechance.ui.home.adapter

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.home.homeUiState.PopularUiState
import com.thechance.viewmodel.home.HomeInteractionListener

class PopularMovieAdapter(items: List<PopularUiState>, listener: HomeInteractionListener) :
    BaseAdapter<PopularUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_popular_movie
}

