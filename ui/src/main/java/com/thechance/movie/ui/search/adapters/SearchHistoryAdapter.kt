package com.thechance.movie.ui.search.adapters

import com.thechance.movie.ui.search.mediaSearchUIState.SearchHistoryUIState
import com.thechance.movie.R
import com.thechance.movie.ui.base.BaseAdapter
import com.thechance.movie.ui.base.BaseInteractionListener


class SearchHistoryAdapter(items: List<SearchHistoryUIState>, listener: SearchHistoryInteractionListener)
    : BaseAdapter<SearchHistoryUIState>(items,listener){
    override val layoutID: Int = R.layout.item_search_history
}

interface SearchHistoryInteractionListener : BaseInteractionListener {
    fun onClickSearchHistory(name: String)
}