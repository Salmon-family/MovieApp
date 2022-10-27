package com.karrar.movieapp.domain.mappers.series

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor(): Mapper<WatchHistory, WatchHistoryEntity> {
    override fun map(input: WatchHistory): WatchHistoryEntity {
        return WatchHistoryEntity(
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