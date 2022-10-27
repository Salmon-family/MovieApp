package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.WatchHistoryUIState
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class TvShowWatchHistoryMapper @Inject constructor(): Mapper<WatchHistoryUIState, WatchHistory> {
    override fun map(input: WatchHistoryUIState): WatchHistory {
        return WatchHistory(
            id = input.id,
            posterPath = input.posterPath,
            movieTitle = input.movieTitle,
            movieDuration = input.movieDuration,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            mediaType = Constants.TV_SHOWS
        )
    }

}