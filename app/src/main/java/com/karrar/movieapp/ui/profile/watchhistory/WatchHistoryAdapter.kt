package com.karrar.movieapp.ui.profile.watchhistory

import com.karrar.movieapp.R
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class WatchHistoryAdapter(
    items: List<WatchHistoryEntity>,
    listener: WatchHistoryInteractionListener,
) : BaseAdapter<WatchHistoryEntity>(items, listener) {
    override val layoutID: Int = R.layout.item_watch_history
}

interface WatchHistoryInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieID: Int)
}