package com.karrar.movieapp.ui.myList

import com.karrar.movieapp.R
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.myList.myListUIState.CreatedListUIState

class CreatedListAdapter(items: List<CreatedListUIState>, listener: CreatedListInteractionListener) :
    BaseAdapter<CreatedListUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_saved_list
}

interface CreatedListInteractionListener : BaseInteractionListener {
    fun onListClick(itemID: Int)
}