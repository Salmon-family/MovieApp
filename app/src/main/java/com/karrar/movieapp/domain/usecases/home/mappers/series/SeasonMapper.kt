package com.karrar.movieapp.domain.usecases.home.mappers.series

import com.karrar.movieapp.BuildConfig
import com.thechance.remote.response.SeasonDto
import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Season
import javax.inject.Inject

class SeasonMapper @Inject constructor(
    private val episodeMapper: EpisodeMapper
) : Mapper<SeasonDto, Season> {
    override fun map(input: com.thechance.remote.response.SeasonDto): Season {
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
