package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.remote.response.PersonDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, Media> {
    override fun map(input: MovieDto): Media {
        return Media(
            type = null,
            id = input.id,
            name = input.originalTitle,
            releaseDate = input.releaseDate,
            rate = input.voteAverage?.toFloat(),
            imagePath = Constants.IMAGE_BASE_PATH + input.backdropPath,
            profileImage = null
        )
    }
}