package com.thechance.ui.profile.watchhistory

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.profile.watchhistory.WatchHistoryInteractionListener
import com.thechance.viewmodel.profile.watchhistory.MediaHistoryUiState

class WatchHistoryAdapter(
    items: List<MediaHistoryUiState>,
    listener: WatchHistoryInteractionListener,
) : BaseAdapter<MediaHistoryUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_watch_history
}

