package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.TvShowDetailsResultUIState
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.WatchHistoryUIState
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class TvShowWatchHistoryMapper @Inject constructor() : Mapper<TvShowDetailsResultUIState, WatchHistory> {
    override fun map(input: TvShowDetailsResultUIState): WatchHistory {
        return WatchHistory(
            id = input.tvShowId,
            posterPath = input.tvShowName,
            movieTitle = input.tvShowImage,
            movieDuration = 0,
            voteAverage = input.tvShowVoteAverage,
            releaseDate = input.tvShowReleaseDate,
            mediaType = Constants.TV_SHOWS
        )
    }
}
