package com.karrar.movieapp.ui.movieDetails.saveMovie

import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.movieDetails.saveMovie.uiState.MySavedListUIState

class SaveListAdapter(
    items: List<MySavedListUIState>, listener: SaveListInteractionListener
) : BaseAdapter<MySavedListUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_list
}

interface SaveListInteractionListener : BaseInteractionListener {
    fun onClickSaveList(listID: Int)
}