package com.thechance.viewmodel.category.com.thechance.viewmodel.search

import com.thechance.viewmodel.base.BaseInteractionListener

interface SearchHistoryInteractionListener : BaseInteractionListener {
    fun onClickSearchHistory(name: String)
}