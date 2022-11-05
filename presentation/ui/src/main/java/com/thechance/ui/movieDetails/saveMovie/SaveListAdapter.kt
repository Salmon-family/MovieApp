package com.thechance.ui.movieDetails.saveMovie

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.movieDetails.saveMovie.SaveListInteractionListener
import com.thechance.viewmodel.movieDetails.saveMovie.uiState.MySavedListUIState

class SaveListAdapter(
    items: List<MySavedListUIState>, listener: SaveListInteractionListener
) : BaseAdapter<MySavedListUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_list
}