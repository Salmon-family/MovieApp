package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.models.MediaHistoryUiState
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