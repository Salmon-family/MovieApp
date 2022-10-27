package com.karrar.movieapp.ui.search

import androidx.paging.PagingData
import com.karrar.movieapp.ui.allMedia.Error
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

data class MediaUIState(
    val mediaID: Int,
    val mediaName: String,
    val mediaImage: String,
    val mediaTypes: String,
    val mediaVoteAverage: Float,
    val mediaReleaseDate: String
)

data class SearchHistoryUIState(
    val name: String,
)

data class Error(
    val code: Int,
    val message: String
)