package com.karrar.movieapp.ui.search.uiStatMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.SearchHistory
import com.karrar.movieapp.ui.search.mediaSearchUIState.SearchHistoryUIState
import javax.inject.Inject


class SearchHistoryUIStateMapper @Inject constructor():
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.SearchHistory, SearchHistoryUIState> {
    override fun map(input: com.devfalah.models.SearchHistory): SearchHistoryUIState {
        return SearchHistoryUIState(
            input.name
        )
    }
}