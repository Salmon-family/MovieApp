package com.thechance.ui.myList

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.myList.CreatedListInteractionListener
import com.thechance.viewmodel.myList.myListUIState.CreatedListUIState

class CreatedListAdapter(
    items: List<CreatedListUIState>,
    listener: CreatedListInteractionListener
) :
    BaseAdapter<CreatedListUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_saved_list
}
