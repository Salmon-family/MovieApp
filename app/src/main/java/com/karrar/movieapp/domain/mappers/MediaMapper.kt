package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MediaDto
import com.karrar.movieapp.domain.models.Media
import javax.inject.Inject

class MediaMapper @Inject constructor() : Mapper<MediaDto,Media> {
    override fun map(input: MediaDto): Media {
        return Media(
            type = input.mediaType,
            id = input.id,
            movieName = input.originalTitle,
            seriesName = input.originalName,
            actorName = input.name,
            releaseDate = input.releaseDate,
            rate = input.voteAverage?.toFloat(),
            imagePath = input.backdropPath,
            firstAirDate = input.firstAirDate,
            profilePath = input.profilePath
        )
    }
}