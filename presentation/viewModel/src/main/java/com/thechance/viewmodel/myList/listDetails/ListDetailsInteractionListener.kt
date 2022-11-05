package com.thechance.viewmodel.category.com.thechance.viewmodel.myList.listDetails

import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.myList.listDetails.listDetailsUIState.SavedMediaUIState

interface ListDetailsInteractionListener : BaseInteractionListener {
    fun onItemClick(item: SavedMediaUIState)
}