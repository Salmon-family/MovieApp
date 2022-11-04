package com.devfalah.usecases.home.mappers.series

import com.thechance.remote.response.SeasonDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Season
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class SeasonMapper @Inject constructor(
    private val episodeMapper: EpisodeMapper
) : Mapper<SeasonDto, Season> {
    override fun map(input: SeasonDto): Season {
        return Season(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            input.name ?: "",
            input.airDate ?: "",
            input.seasonNumber ?: 0,
            input.episodeCount ?: 0,
            input.overview ?: "",
            input.episodes?.map {
                episodeMapper.map(it)
            } ?: emptyList()
        )
    }
}
