package com.thechance.ui.search.adapters

import com.thechance.ui.R
import com.thechance.ui.base.BaseAdapter
import com.thechance.viewmodel.category.com.thechance.viewmodel.search.SearchHistoryInteractionListener
import com.thechance.viewmodel.search.mediaSearchUIState.SearchHistoryUIState


class SearchHistoryAdapter(
    items: List<SearchHistoryUIState>,
    listener: SearchHistoryInteractionListener
) : BaseAdapter<SearchHistoryUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_search_history
}

