package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.WatchHistory
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.WatchHistoryUIState
import javax.inject.Inject

class WatchHistoryUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<WatchHistoryUIState, com.devfalah.models.WatchHistory> {
    override fun map(input: WatchHistoryUIState): com.devfalah.models.WatchHistory {
        return com.devfalah.models.WatchHistory(
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
