package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.WatchHistory
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.TvShowDetailsResultUIState
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class TvShowWatchHistoryMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<TvShowDetailsResultUIState, com.devfalah.models.WatchHistory> {
    override fun map(input: TvShowDetailsResultUIState): com.devfalah.models.WatchHistory {
        return com.devfalah.models.WatchHistory(
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
