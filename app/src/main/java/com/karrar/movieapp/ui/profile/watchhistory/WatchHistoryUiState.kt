package com.karrar.movieapp.ui.profile.watchhistory

data class WatchHistoryUiState(
    val allMedia: List<MediaHistoryUiState> = emptyList(),
    val error: List<Error> = emptyList()
)

data class Error(
    val code: Int,
    val message: String,
)