package com.karrar.movieapp.domain.usecases.mappers

import com.karrar.movieapp.ui.profile.watchhistory.MediaHistoryUiState
import com.thechance.local.database.entity.WatchHistoryEntity
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor() : Mapper<WatchHistoryEntity, MediaHistoryUiState> {
    override fun map(input: WatchHistoryEntity): MediaHistoryUiState {
        return MediaHistoryUiState(
            input.id,
            input.posterPath,
            input.movieTitle,
            input.voteAverage,
            input.releaseDate,
            input.movieDuration,
            input.mediaType,
        )
    }
}