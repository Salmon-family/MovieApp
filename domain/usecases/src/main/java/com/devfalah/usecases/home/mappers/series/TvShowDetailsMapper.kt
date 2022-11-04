package com.devfalah.usecases.home.mappers.series

import com.thechance.remote.response.tvShow.TvShowDetailsDto
import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.TvShowDetails
import com.devfalah.types.MediaType
import com.thechance.repository.BuildConfig
import javax.inject.Inject

class TvShowDetailsMapper @Inject constructor(
    private val seasonMapper: SeasonMapper,
) :
    Mapper<TvShowDetailsDto, TvShowDetails> {
    override fun map(input: TvShowDetailsDto): TvShowDetails {
        return TvShowDetails(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            input.name ?: "",
            input.firstAirDate?.toString() ?: "",
            input.genres?.map { it?.name }?.joinToString(", ") ?: "",
            input.numberOfSeasons ?: 0,
            input.voteCount ?: 0,
            input.voteAverage.toString().take(3),
            input.overview ?: "",
            input.season?.map { seasonMapper.map(it) } ?: emptyList(),
        )
    }
}