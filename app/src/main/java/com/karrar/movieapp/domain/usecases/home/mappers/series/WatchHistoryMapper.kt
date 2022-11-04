package com.karrar.movieapp.domain.usecases.home.mappers.series

import com.thechance.local.database.entity.WatchHistoryEntity
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.TvShowDetails
import javax.inject.Inject

class WatchHistoryMapper @Inject constructor(): Mapper<TvShowDetails, WatchHistoryEntity> {
    override fun map(input: TvShowDetails): com.thechance.local.database.entity.WatchHistoryEntity {
        return com.thechance.local.database.entity.WatchHistoryEntity(
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