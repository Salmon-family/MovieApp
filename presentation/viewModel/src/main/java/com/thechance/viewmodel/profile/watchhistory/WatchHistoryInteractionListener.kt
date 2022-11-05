package com.thechance.viewmodel.category.com.thechance.viewmodel.profile.watchhistory

import com.thechance.viewmodel.base.BaseInteractionListener
import com.thechance.viewmodel.profile.watchhistory.MediaHistoryUiState

interface WatchHistoryInteractionListener : BaseInteractionListener {
    fun onClickMovie(item: MediaHistoryUiState)
}