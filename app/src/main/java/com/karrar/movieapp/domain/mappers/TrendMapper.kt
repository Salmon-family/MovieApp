package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.TrendingDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class TrendMapper @Inject constructor() : Mapper<TrendingDto, Media> {
    override fun map(input: TrendingDto): Media {
        return Media(
            mediaID = input.id ?: 0,
            mediaImage = Constants.IMAGE_BASE_PATH + input.posterPath,
            mediaType = input.mediaType ?: Constants.TV_SHOWS
        )
    }
}