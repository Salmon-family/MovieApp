package com.thechance.viewmodel.search.uiStatMapper

import com.devfalah.models.SearchHistory
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.search.mediaSearchUIState.SearchHistoryUIState
import javax.inject.Inject


class SearchHistoryUIStateMapper @Inject constructor() :
    Mapper<SearchHistory, SearchHistoryUIState> {
    override fun map(input: SearchHistory): SearchHistoryUIState {
        return SearchHistoryUIState(
            input.name
        )
    }
}