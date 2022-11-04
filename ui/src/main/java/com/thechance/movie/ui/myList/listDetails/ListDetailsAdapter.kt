package com.thechance.movie.ui.myList.listDetails

import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener
import com.thechance.movie.ui.myList.listDetails.listDetailsUIState.SavedMediaUIState

class ListDetailsAdapter(
    lists: List<SavedMediaUIState>,
    listener: ListDetailsInteractionListener
) : BaseAdapter<SavedMediaUIState>(lists,listener) {
    override val layoutID  = R.layout.item_list_details
}
interface ListDetailsInteractionListener : BaseInteractionListener {
    fun onItemClick(item: SavedMediaUIState)
}