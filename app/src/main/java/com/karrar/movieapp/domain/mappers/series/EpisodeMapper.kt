package com.karrar.movieapp.domain.mappers.series

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.episode.EpisodeDto
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Episode
import javax.inject.Inject

class EpisodeMapper @Inject constructor() : Mapper<EpisodeDto, Episode> {
    override fun map(input: EpisodeDto): Episode {
        return Episode(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.stillPath,
            input.name ?: "",
            input.airDate?.take(4) ?: "",
            input.airDate ?: "",
            input.voteAverage ?: 0.0,
            input.voteCount.toString(),
            input.overview ?: ""
        )
    }
}