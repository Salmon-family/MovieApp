package com.thechance.movie.ui.profile.watchhistory

import com.devfalah.usecases.mappers.Mapper
import com.thechance.local.database.entity.WatchHistoryEntity
import javax.inject.Inject

class WatchHistoryUIStateMapper @Inject constructor() :
    Mapper<WatchHistoryEntity, MediaHistoryUiState> {
    override fun map(input: WatchHistoryEntity): MediaHistoryUiState {
        return MediaHistoryUiState(
            id = input.id,
            posterPath = input.posterPath,
            movieTitle = input.movieTitle,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            movieDuration = input.movieDuration,
            mediaType = input.mediaType
        )
    }
}