package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.SeriesDto
import com.karrar.movieapp.data.remote.response.TrendingDto
import com.karrar.movieapp.domain.models.MediaInfo
import com.karrar.movieapp.domain.models.Trend
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class TrendMapper @Inject constructor() : Mapper<TrendingDto, Trend> {
    override fun map(input: TrendingDto): Trend {
        return Trend(
            id = input.id,
            imageUrl = Constants.IMAGE_BASE_PATH + input.posterPath,
            mediaType = input.mediaType
        )
    }
}