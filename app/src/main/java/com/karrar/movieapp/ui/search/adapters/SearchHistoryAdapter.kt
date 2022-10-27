package com.karrar.movieapp.ui.search.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.SearchHistory
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener
import com.karrar.movieapp.ui.search.SearchHistoryUIState

class SearchHistoryAdapter(items: List<SearchHistoryUIState>, listener: SearchHistoryInteractionListener)
    : BaseAdapter<SearchHistoryUIState>(items,listener){
    override val layoutID: Int = R.layout.item_search_history
}

interface SearchHistoryInteractionListener : BaseInteractionListener {
    fun onClickSearchHistory(name: String)
}