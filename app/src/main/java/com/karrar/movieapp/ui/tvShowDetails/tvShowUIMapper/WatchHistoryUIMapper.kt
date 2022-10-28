package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.WatchHistoryUIState
import javax.inject.Inject

class WatchHistoryUIMapper @Inject constructor() : Mapper<WatchHistoryUIState, WatchHistory> {
    override fun map(input: WatchHistoryUIState): WatchHistory {
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
