package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.TrendingDto
import com.karrar.movieapp.domain.models.Trend
import javax.inject.Inject

class TrendMapper @Inject constructor() : Mapper<TrendingDto, Trend> {
    override fun map(input: TrendingDto): Trend {
        return Trend(
            id = input.id,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            mediaType = input.mediaType
        )
    }
}