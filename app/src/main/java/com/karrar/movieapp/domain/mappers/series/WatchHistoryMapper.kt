package com.karrar.movieapp.domain.mappers.series

import com.karrar.movieapp.data.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.domain.models.WatchHistory
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor(): Mapper<TvShowDetails, WatchHistoryEntity> {
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