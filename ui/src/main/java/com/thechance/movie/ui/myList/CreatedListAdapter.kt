package com.thechance.movie.ui.myList

import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.myList.myListUIState.CreatedListUIState

class CreatedListAdapter(items: List<CreatedListUIState>, listener: CreatedListInteractionListener) :
    BaseAdapter<CreatedListUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_saved_list
}

interface CreatedListInteractionListener : BaseInteractionListener {
    fun onListClick(item: CreatedListUIState)
}