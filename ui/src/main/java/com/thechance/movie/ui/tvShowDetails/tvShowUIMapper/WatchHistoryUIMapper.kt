package com.thechance.movie.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.WatchHistory
import com.thechance.movie.ui.tvShowDetails.tvShowUIState.WatchHistoryUIState
import javax.inject.Inject

class WatchHistoryUIMapper @Inject constructor() :
    Mapper<WatchHistoryUIState, WatchHistory> {
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
