package com.karrar.movieapp.domain.mappers.movie

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.WatchHistory
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor() : Mapper<WatchHistory, WatchHistoryEntity> {
    override fun map(input: WatchHistory): WatchHistoryEntity {
        return WatchHistoryEntity(
            id = input.id,
            posterPath = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            movieTitle = input.movieTitle,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            movieDuration = input.movieDuration,
            mediaType = input.mediaType
        )
    }
}