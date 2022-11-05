package com.thechance.viewmodel.myList

import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.myList.myListUIState.CreatedListUIState

interface CreatedListInteractionListener : BaseInteractionListener {
    fun onListClick(item: CreatedListUIState)
}