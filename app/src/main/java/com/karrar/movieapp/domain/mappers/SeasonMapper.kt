package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.SeasonDto
import com.karrar.movieapp.domain.models.Season
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class SeasonMapper @Inject constructor(
    private val episodeMapper: EpisodeMapper
) : Mapper<SeasonDto, Season> {
    override fun map(input: SeasonDto): Season {
        return Season(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.posterPath,
            input.name ?: "",
            input.airDate?.take(4) ?: "",
            input.seasonNumber ?: 0,
            input.episodeCount ?: 0,
            input.overview ?: "",
            input.episodes?.map {
                episodeMapper.map(it)
            } ?: emptyList()
        )
    }
}