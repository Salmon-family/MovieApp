package com.karrar.movieapp.ui.search.adapters

import com.karrar.movieapp.R
import com.karrar.movieapp.domain.models.SearchHistory
import com.karrar.movieapp.ui.base.BaseAdapter
import com.karrar.movieapp.ui.base.BaseInteractionListener

class SearchHistoryAdapter(items: List<SearchHistory>, listener: SearchHistoryInteractionListener)
    : BaseAdapter<SearchHistory>(items,listener){
    override val layoutID: Int = R.layout.item_search_history
}

interface SearchHistoryInteractionListener : BaseInteractionListener {
    fun onClickSearchHistory(name: String)
}