package com.thechance.viewmodel.search.mediaSearchUIState

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class MediaSearchUIState(
    val searchInput: String = "",
    val searchTypes: MediaTypes = MediaTypes.MOVIE,
    val searchResult: Flow<PagingData<MediaUIState>> = emptyFlow(),
    val searchHistory: List<SearchHistoryUIState> = emptyList(),
    val isLoading : Boolean = false,
    val isEmpty: Boolean = false,
    val error : List<Error> = emptyList(),
)