package com.karrar.movieapp.ui.saved

import com.karrar.movieapp.R
import com.karrar.movieapp.data.local.database.entity.Lists
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.domain.models.CreatedLists
import com.karrar.movieapp.ui.base.BaseInteractionListener

class SavedListsAdapter(
    lists: List<Lists>,
    listener: SavedListInteractionListener
) : BaseAdapter<Lists>(lists,listener) {
    override val layoutID  = R.layout.saved_category

}
interface SavedListInteractionListener : BaseInteractionListener {

     fun onShowListItems(item: Lists)

}