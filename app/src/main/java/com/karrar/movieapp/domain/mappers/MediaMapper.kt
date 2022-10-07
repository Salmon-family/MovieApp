package com.karrar.movieapp.domain.mappers

import com.karrar.movieapp.data.remote.response.movie.MovieDto
import com.karrar.movieapp.domain.models.Media
import com.karrar.movieapp.utilities.Constants
import javax.inject.Inject

class MediaMapper @Inject constructor() : Mapper<MovieDto, Media> {
    override fun map(input: MovieDto): Media {
        return Media(
            input.id ?: 0,
            Constants.IMAGE_BASE_PATH + input.posterPath,
            Constants.MOVIE
        )
    }
}