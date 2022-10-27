package com.karrar.movieapp.ui.profile.watchhistory

import com.karrar.movieapp.domain.models.MediaHistoryUiState

data class WatchHistoryUiState(
    val allMedia: List<MediaHistoryUiState> = emptyList(),
    val error: List<Error> = emptyList()
)

data class Error(
    val code: Int,
    val message: String,
)