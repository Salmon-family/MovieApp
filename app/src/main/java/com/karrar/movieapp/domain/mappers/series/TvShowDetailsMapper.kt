package com.karrar.movieapp.domain.mappers.series

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.domain.enums.MediaType
import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.TvShowDetails
import com.karrar.movieapp.utilities.convertToDayMonthYearFormat
import com.thechance.repository.remote.response.tvShow.TvShowDetailsDto
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
            input.firstAirDate?.convertToDayMonthYearFormat() ?: "",
            input.genres?.map { it?.name }?.joinToString(", ") ?: "",
            input.numberOfSeasons ?: 0,
            input.voteCount ?: 0,
            input.voteAverage.toString().take(3),
            input.overview ?: "",
            input.season?.map { seasonMapper.map(it) } ?: emptyList(),
            MediaType.TV_SHOW
        )
    }
}