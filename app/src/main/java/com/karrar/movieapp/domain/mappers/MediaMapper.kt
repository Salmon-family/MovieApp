package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MediaDto
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.domain.models.Movie
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class MediaMapper @Inject constructor() : Mapper<MediaDto,Media> {
    override fun map(input: MediaDto): Media {
        return Media(
            type = input.mediaType,
            id = input.id,
            name = listOf(input.originalName, input.name, input.originalTitle).first { it != null },
            releaseDate = input.releaseDate?.substringBefore('-'),
            rate = input.voteAverage?.toFloat(),
            imagePath = Constants.IMAGE_BASE_PATH + input.backdropPath ,
            firstAirDate = input.firstAirDate,
            profilePath = Constants.IMAGE_BASE_PATH + input.profilePath
        )
    }
}