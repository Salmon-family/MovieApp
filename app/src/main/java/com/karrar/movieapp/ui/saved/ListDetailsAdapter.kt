package com.karrar.movieapp.ui.saved

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.SaveListDetails
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class ListDetailsAdapter(
    lists: List<SaveListDetails>,
    listener: ListDetailsInteractionListener
) : BaseAdapter<SaveListDetails>(lists,listener) {
    override val layoutID  = R.layout.item_list

}
interface ListDetailsInteractionListener : BaseInteractionListener {

    fun onShowListItems(item: SaveListDetails)

}