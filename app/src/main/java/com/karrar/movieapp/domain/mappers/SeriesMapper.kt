package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.SeriesDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class SeriesMapper @Inject constructor() : Mapper<SeriesDto, Media> {
    override fun map(input: SeriesDto): Media {
        return Media(
            type = null,
            id = input.id,
            name = input.originalName,
            releaseDate = input.firstAirDate,
            rate = input.voteAverage?.toFloat(),
            imagePath = Constants.IMAGE_BASE_PATH + input.backdropPath,
            profileImage = null
        )
    }
}