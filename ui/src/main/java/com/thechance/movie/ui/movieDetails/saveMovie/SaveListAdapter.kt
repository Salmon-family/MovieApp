package com.thechance.movie.ui.movieDetails.saveMovie

import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.movieDetails.saveMovie.uiState.MySavedListUIState

class SaveListAdapter(
    items: List<MySavedListUIState>, listener: SaveListInteractionListener
) : BaseAdapter<MySavedListUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_list
}

interface SaveListInteractionListener : BaseInteractionListener {
    fun onClickSaveList(listID: Int)
}