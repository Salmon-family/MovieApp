package com.thechance.movie.ui.search.uiStatMapper

import com.thechance.movie.ui.search.mediaSearchUIState.SearchHistoryUIState
import javax.inject.Inject


class SearchHistoryUIStateMapper @Inject constructor():
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.SearchHistory, SearchHistoryUIState> {
    override fun map(input: com.devfalah.models.SearchHistory): SearchHistoryUIState {
        return SearchHistoryUIState(
            input.name
        )
    }
}