package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.tvShow.TvShowDetailsDto
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class TvShowDetailsMapper @Inject constructor(
    private val seasonMapper: SeasonMapper
) :
    Mapper<TvShowDetailsDto, TvShowDetails> {
    override fun map(input: TvShowDetailsDto): TvShowDetails {
        return TvShowDetails(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.posterPath,
            input.name ?: "",
            input.firstAirDate?.take(4) ?: "unknown",
            input.genres?.map { it?.name }?.joinToString(" , ") ?: "unknown",
            input.episodeRunTime?.get(0) ?: 0,
            input.voteCount ?: 0,
            input.voteAverage.toString().take(3),
            input.overview ?: "",
            input.season?.map {
                seasonMapper.map(it)
            } ?: emptyList()
        )
    }
}