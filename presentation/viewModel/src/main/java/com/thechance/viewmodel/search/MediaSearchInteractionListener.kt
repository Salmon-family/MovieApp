package com.thechance.viewmodel.category.com.thechance.viewmodel.search

import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.search.mediaSearchUIState.MediaUIState

interface MediaSearchInteractionListener : BaseInteractionListener {
    fun onClickMediaResult(media: MediaUIState)
}