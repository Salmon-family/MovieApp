package com.thechance.ui.myList.listDetails

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.myList.listDetails.ListDetailsInteractionListener
import com.thechance.viewmodel.myList.listDetails.listDetailsUIState.SavedMediaUIState

class ListDetailsAdapter(
    lists: List<SavedMediaUIState>,
    listener: ListDetailsInteractionListener
) : BaseAdapter<SavedMediaUIState>(lists, listener) {
    override val layoutID = R.layout.item_list_details
}
