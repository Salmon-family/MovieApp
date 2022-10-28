package com.karrar.movieapp.ui.myList.listDetails

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.category.uiState.MediaUIState
import com.karrar.movieapp.ui.myList.listDetails.listDetailsUIState.SavedMediaUIState

class ListDetailsAdapter(
    lists: List<SavedMediaUIState>,
    listener: ListDetailsInteractionListener
) : BaseAdapter<SavedMediaUIState>(lists,listener) {
    override val layoutID  = R.layout.item_list_details
}
interface ListDetailsInteractionListener : BaseInteractionListener {
    fun onItemClick(item: SavedMediaUIState)
}