package com.karrar.movieapp.ui.movieDetails.mapExtension

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.WatchHistoryUiState
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor() : Mapper<WatchHistoryUiState, WatchHistory> {
    override fun map(input: WatchHistoryUiState): WatchHistory {
        return WatchHistory(
            id = input.id,
            posterPath = input.posterPath,
            movieTitle = input.movieTitle,
            movieDuration = input.movieDuration,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            mediaType = input.mediaType
        )
    }
}