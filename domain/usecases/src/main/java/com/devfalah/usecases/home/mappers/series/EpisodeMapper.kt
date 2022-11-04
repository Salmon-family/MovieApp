package com.devfalah.usecases.home.mappers.series

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Episode
import com.thechance.remote.response.episode.EpisodeDto
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class EpisodeMapper @Inject constructor() : Mapper<EpisodeDto, Episode> {
    override fun map(input: EpisodeDto): Episode {
        return Episode(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.stillPath,
            input.name ?: "",
            input.runtime ?: 0,
            input.airDate ?: "",
            input.voteAverage ?: 0.0,
            input.voteCount.toString(),
            input.overview ?: "",
            input.episodeNumber ?: 1,
        )
    }
}