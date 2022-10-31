package com.example.usecase.mappers.series

import com.example.models.models.TvShowDetails
import com.example.models.utilities.MediaType
import com.example.usecase.mappers.Mapper
import com.example.usecase.utilites.convertToDayMonthYearFormat
import com.thechance.repository.BuildConfig
import com.thechance.repository.remote.response.tvShow.TvShowDetailsDto
import javax.inject.Inject

class TvShowDetailsMapper @Inject constructor(
    private val seasonMapper: SeasonMapper
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
