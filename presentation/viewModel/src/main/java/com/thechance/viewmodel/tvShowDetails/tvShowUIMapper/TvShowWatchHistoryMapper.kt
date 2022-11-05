package com.thechance.viewmodel.tvShowDetails.tvShowUIMapper

import com.devfalah.models.WatchHistory
import com.devfalah.usecases.mappers.Mapper
import com.thechance.ui.utilities.Constants
import com.thechance.viewmodel.tvShowDetails.tvShowUIState.TvShowDetailsResultUIState
import javax.inject.Inject

class TvShowWatchHistoryMapper @Inject constructor() :
    Mapper<TvShowDetailsResultUIState, WatchHistory> {
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
