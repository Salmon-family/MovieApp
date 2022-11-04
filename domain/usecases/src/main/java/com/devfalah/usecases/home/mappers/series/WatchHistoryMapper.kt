package com.devfalah.usecases.home.mappers.series

import com.thechance.local.database.entity.WatchHistoryEntity
import com.devfalah.types.MediaType
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.TvShowDetails
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor():
    Mapper<TvShowDetails, WatchHistoryEntity> {
    override fun map(input: TvShowDetails): WatchHistoryEntity {
        return WatchHistoryEntity(
            id = input.tvShowId,
            posterPath = input.tvShowImage,
            movieTitle = input.tvShowName,
            movieDuration = input.tvShowSeasonsNumber,
            voteAverage = input.tvShowVoteAverage,
            releaseDate = input.tvShowReleaseDate,
            mediaType = MediaType.TV_SHOW.value
        )
    }

}