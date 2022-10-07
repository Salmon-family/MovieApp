package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.SeriesDto
import com.karrar.movieapp.domain.models.MediaInfo
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class SeriesMapper @Inject constructor() : Mapper<SeriesDto, MediaInfo> {
    override fun map(input: SeriesDto): MediaInfo {
        return MediaInfo(
            type = Constants.TV_SHOWS,
            id = input.id ?: 0,
            name = input.originalName ?: "",
            releaseDate = input.firstAirDate?.substringBefore('-') ?: "",
            rate = input.voteAverage?.toFloat() ?: 0F,
            imagePath = Constants.IMAGE_BASE_PATH + input.backdropPath,
        )
    }
}